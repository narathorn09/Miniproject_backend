package com.boardgame.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.main.model.User;
import com.boardgame.main.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user")
	public ResponseEntity<Object> getUsers() {
		
		try {
			List<User> listUsers = userRepository.findAll();
			return new ResponseEntity<>(listUsers, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User body){
		
		try {
			
			User newUser = userRepository.save(body);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{userID}")
	public ResponseEntity<Object> getUserById(@PathVariable("userID") Long userID) {
		
		try {	
		
			Optional<User> userFound = userRepository.findById(userID);
			if(userFound.isPresent()) {
				return new ResponseEntity<>(userFound, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("User Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/user/{userID}")
	public ResponseEntity<Object> updateEmployee(@PathVariable("userID") Long userID, @RequestBody User body) {
		
		try {
			
			Optional<User> userFound = userRepository.findById(userID);
			
			if(userFound.isPresent()) {
				User userEdit = userFound.get();
				
				userEdit.setUsername(body.getUsername());
				userEdit.setPassword(body.getPassword());
				userEdit.setUserType(body.getUserType());
				
				userRepository.save(userEdit);
				
				return new ResponseEntity<>(userEdit, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("User Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@DeleteMapping("/user/{userID}")
	public ResponseEntity<Object> deleteUserById(@PathVariable("userID") Long userID) {
		
		try {
			
			Optional<User> userFound = userRepository.findById(userID);
			
			if(userFound.isPresent()) {
				userRepository.delete(userFound.get());
				return new ResponseEntity<>("Delete User Success.", HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("User Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/checkUsername")
	public ResponseEntity<Object> checkUsername(@RequestParam("username") String username) {
		
		try {
			Optional<User> userFound = userRepository.findByUsername(username);

			if(userFound.isPresent()) {
				return new ResponseEntity<>("User Found.", HttpStatus.OK);
		
			}else {
				return new ResponseEntity<>("User Not Found.", HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
}
