package com.capg.hotelbookingmanagementsystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.UserNotFoundException;
import com.capg.hotelbookingmanagementsystem.repository.UserRepository;
import com.capg.hotelbookingmanagementsystem.service.UserService;
import com.capg.hotelbookingmanagementsystem.sort.SortUserByEmailAs;
import com.capg.hotelbookingmanagementsystem.sort.SortUserByNameAs;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		if(user == null)
			throw new UserNotFoundException("Invalid Customer Data");
		return userRepository.saveAndFlush(user);
	}

	@Override 
	public User updateUser(User user) { 
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String removeUserById(int userId) { 
		userRepository.deleteById(userId); return "User deleted"; 
	}

	@Override 
	public List<User> showAllUsers(){
		List<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<User>();
		users.forEach(i -> {
			User u = i;
//			u.setBooking(null);
			userList.add(u);
				});
		return userList;
	}

	@Override
	public User showUserById(int userId) { 
		User u = userRepository.findById(userId).get(); 
//		u.setBooking(null);
		return u;
	}


	@Override
	public boolean validateUser(String userName, String password) {
		Optional<User> user = userRepository.findByUsernameAndPassword(userName, password);
		if(user.get() == null)
			return false;
		else
			return true;
	}
	public Optional<User> viewByUserName(String userName, String password) {
		Optional<User> user = userRepository.findByUsernameAndPassword(userName, password);
		if(user.get() == null)
			throw new UserNotFoundException("Customer not created");
		return user;		
	}
	
	public boolean findIfIdPresent(int userId) {
		if(userRepository.findById(userId).isPresent())
			return true;
	    return false;
	}
	
	public List<User> sortByNameAs(){
		List<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<User>();
		users.forEach(i -> {
			User u = i;
//			u.setBooking(null);
			userList.add(u);
				});
		 Collections.sort(userList,new SortUserByNameAs());
		 return userList;
	}
	
	public List<User> sortByEmailAs(){
		List<User> users = userRepository.findAll();
		List<User> userList = new ArrayList<User>();
		users.forEach(i -> {
			User u = i;
//			u.setBooking(null);
			userList.add(u);
				});
		 Collections.sort(userList,new SortUserByEmailAs());
		 return userList;
	}
	
	public boolean userPresentByUsername(String username){
		User user = this.userRepository.findByUsername(username);
		if(user == null)
			return false;
		return true;
		
	}
}


