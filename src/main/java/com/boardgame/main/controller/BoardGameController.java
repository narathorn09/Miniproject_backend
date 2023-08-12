package com.boardgame.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boardgame.main.model.BoardGame;
import com.boardgame.main.model.User;
import com.boardgame.main.repository.BoardGameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BoardGameController {

	@Autowired
	BoardGameRepository boardGameRepository;
	
	public BoardGameController(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }
	
	@GetMapping("/boardgame")
	public ResponseEntity<Object> getBoardGames() {
		
		try {
			List<BoardGame> listBoardGames = boardGameRepository.findAllBoardGame();
			List<BoardGame> boardGames = new ArrayList<>();

		    for (BoardGame row : listBoardGames) {
		    	 // Ensure that the array has at least 7 elements
		            Long gameID = row.getGameID();
		            String title = row.getTitle();
		            String description = row.getDescription();
		            String photoName = row.getPhotoName();
		            byte[] photoData = row.getPhotoData();
		            Float adminRating = row.getAdminRating();
		            Float averageRating = row.getAverageRating();
		            User user = row.getUser();
		            
		            user.setPassword(null);
		            user.setUsername(null);
		            user.setUserType(null);
		            
		      		BoardGame boardGame = new BoardGame(gameID, title, description, photoName, photoData, adminRating, averageRating, user);

		            boardGames.add(boardGame);
			    }
			return new ResponseEntity<>(boardGames, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/boardgame", consumes = {"multipart/form-data"})
	public ResponseEntity<Object> createBoardGame(
	        @RequestParam("body") String boardGameJson,
	        @RequestParam("photo") MultipartFile photo) throws IOException {

	    try {
	        BoardGame body = new ObjectMapper().readValue(boardGameJson, BoardGame.class);
	        
	        if(!photo.isEmpty()) {
	        	String photoName = UUID.randomUUID().toString() + ".png";
	        	body.setPhotoName(photoName);
		        body.setPhotoData(photo.getBytes());
	        }
	        
	        BoardGame newBoardGame = boardGameRepository.save(body);
	        return new ResponseEntity<>(newBoardGame, HttpStatus.CREATED);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@GetMapping("/boardgame/{gameID}")
	public ResponseEntity<Object> getBoardGameById(@PathVariable("gameID") Long gameID) {
		
		try {	
		
			Optional<BoardGame> boardgameFound = boardGameRepository.findById(gameID);
			if(boardgameFound.isPresent()) {
				BoardGame boardGame = boardgameFound.get();
				
				User user = boardGame.getUser();
	            user.setUsername(null);
	            user.setPassword(null);
	            user.setUserType(null);
	            
				return new ResponseEntity<>(boardGame, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Board Game Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value = "/boardgame/{gameID}", consumes = {"multipart/form-data"})
	public ResponseEntity<Object> updateBoardGame(
			@PathVariable("gameID") Long gameID, 
			@RequestParam("body") String boardGameJson, 
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			
			Optional<BoardGame> boardgameFound = boardGameRepository.findById(gameID);
			
			if(boardgameFound.isPresent()) {
				BoardGame body = new ObjectMapper().readValue(boardGameJson, BoardGame.class);
				BoardGame boardgameEdit = boardgameFound.get();
				
				if(!photo.isEmpty()) {
		        	String photoName = UUID.randomUUID().toString() + ".png";
		        	body.setPhotoName(photoName);
			        body.setPhotoData(photo.getBytes());
		        }
				
				boardgameEdit.setTitle(body.getTitle());
				boardgameEdit.setDescription(body.getDescription());
				boardgameEdit.setPhotoName(body.getPhotoName());
				boardgameEdit.setPhotoData(body.getPhotoData());
				boardgameEdit.setAdminRating(body.getAdminRating());
				boardgameEdit.setAverageRating(body.getAverageRating());
				boardgameEdit.setUser(body.getUser());
				
				boardGameRepository.save(boardgameEdit);
				
				return new ResponseEntity<>(boardgameEdit, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Board Game Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@DeleteMapping("/boardgame/{gameID}")
	public ResponseEntity<Object> deleteBoardGameById(@PathVariable("gameID") Long gameID) {
		
		try {
			
			Optional<BoardGame> boardgameFound = boardGameRepository.findById(gameID);
			
			if(boardgameFound.isPresent()) {
				boardGameRepository.delete(boardgameFound.get());
				return new ResponseEntity<>("Delete Board Game Success.", HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Board Game Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/searchBoardGame")
	public ResponseEntity<Object> searchBoardGames(@RequestParam("title") String title) {
		
		try {
			List<BoardGame> boardgameFound = boardGameRepository.findBoardGameByTitle(title);
			
			if(!boardgameFound.isEmpty()) {
				return new ResponseEntity<>(boardgameFound, HttpStatus.OK);

			}else {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	


}
