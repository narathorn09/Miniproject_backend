package com.boardgame.main.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.main.model.Review;
import com.boardgame.main.model.User;
import com.boardgame.main.repository.ReviewRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReviewController {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@GetMapping("/review/{gameID}")
	public ResponseEntity<Object> getReviewByGameId(@PathVariable("gameID") Long gameID) {
		
		try {
			List<Object[]> listReviews = reviewRepository.findReviewByGameId(gameID);
			List<Review> reviews = new ArrayList<>();

		    for (Object[] row : listReviews) {
		        Long reviewID = (Long) row[0];
		        Float rating = (Float) row[1];
		        String comment = (String) row[2];
		        Timestamp timestamp = (Timestamp) row[3];
		        User user = (User) row[4];
		        
		        user.setPassword(null);
		        user.setUserType(null);
		        
		        Review review = new Review(reviewID, rating, comment, timestamp, null, user);

		        reviews.add(review);
		    }
		    
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/review")
	public ResponseEntity<Object> createReview(@RequestBody Review body){
		
		try {
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
		    body.setTimestamp(now);
		    
			Review newReview = reviewRepository.save(body);
			return new ResponseEntity<>(newReview, HttpStatus.CREATED);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/review/{reviewID}")
	public ResponseEntity<Object> updateReview(@PathVariable("reviewID") Long reviewID, @RequestBody Review body) {
		
		try {
			
			Optional<Review> reviewFound = reviewRepository.findById(reviewID);
			
			if(reviewFound.isPresent()) {
				
				Review reviewEdit = reviewFound.get();
				
				reviewEdit.setRating(body.getRating());
				reviewEdit.setComment(body.getComment());
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				reviewEdit.setTimestamp(now);
				reviewEdit.setGame(null);
				reviewEdit.setUser(null);
				
				reviewRepository.save(reviewEdit);
				
				return new ResponseEntity<>(reviewEdit, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Review Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@DeleteMapping("/review/{reviewID}")
	public ResponseEntity<Object> deleteReviewById(@PathVariable("reviewID") Long reviewID) {
		
		try {
			
			Optional<Review> reviewFound = reviewRepository.findById(reviewID);
			
			if(reviewFound.isPresent()) {
				reviewRepository.delete(reviewFound.get());
				return new ResponseEntity<>("Delete Review Success.", HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Review Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
}
