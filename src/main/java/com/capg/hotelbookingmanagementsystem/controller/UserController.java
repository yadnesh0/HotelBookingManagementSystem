package com.capg.hotelbookingmanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("NORMAL");
		user.setEnabled(true);
		User u = userService.addUser(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}


	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws RecordNotFoundException{
		Integer id=user.getUserId();
		if(userService.findIfIdPresent(id)){
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole("NORMAL");
			user.setEnabled(true);
			User u = userService.updateUser(user);
			return new ResponseEntity<User>(u,HttpStatus.CREATED);
		}
		else {
			throw new RecordNotFoundException("User with Id : "+id+" not found ");
		}
	}
	
	@PutMapping("/updateAdmin")
	public ResponseEntity<User> updateAdmin(@RequestBody User user) throws RecordNotFoundException{
		Integer id=user.getUserId();
		if(userService.findIfIdPresent(id)){
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole("ADMIN");
			user.setEnabled(true);
			User u = userService.updateUser(user);
			return new ResponseEntity<User>(u,HttpStatus.CREATED);
		}
		else {
			throw new RecordNotFoundException("User with Id : "+id+" not found ");
		}
	}

	@GetMapping("/listUser")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>( userService.showAllUsers(),HttpStatus.OK);
	}


	@DeleteMapping("/deletedById/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId) throws RecordNotFoundException{

		if(userService.findIfIdPresent(userId)) {
			String user = userService.removeUserById(userId);
			return new ResponseEntity<String>(user,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("User with Id : "+userId+" not found ");
		}
	}


	@GetMapping("/getById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws RecordNotFoundException{

		if(userService.findIfIdPresent(userId)) {
			User user = userService.showUserById(userId);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("User with Id : "+userId+" not found ");
		}
	}
	
	@GetMapping("/nameAs") 
	 public ResponseEntity<List<User>> sortByNameAscending() {
		 return new ResponseEntity<List<User>>(userService.sortByNameAs(),HttpStatus.OK);
	 }
	
	@GetMapping("/email") 
	 public ResponseEntity<List<User>> sortByEmail() {
		 return new ResponseEntity<List<User>>(userService.sortByEmailAs(),HttpStatus.OK);
	 }
}


