package com.capg.hotelbookingmanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.service.impl.UserServiceImpl;

@SpringBootApplication
public class HotelBookingManagementSystemApplication implements CommandLineRunner{

	@Autowired
	private UserServiceImpl userService;
	
//	@Autowired
//	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code");
		boolean isPresent = userService.userPresentByUsername("admin22");
		
		if(!isPresent){
		User user = new User();
		
		user.setUserId(22);
		user.setUsername("admin22");
		user.setPassword(this.bCryptPasswordEncoder.encode("Admin@22"));
		user.setRole("ADMIN");
		user.setFirstName("Y");
		user.setLastName("D");
		user.setEmail("admin@hbms.com");
		user.setEnabled(true);
		user.setMobile("8956457845");
		user.setAddress("Mumbai");
		
		User user1 = userService.addUser(user);
		System.out.println(user1.getUsername());
		}
		
//		List<User> l = this.userRepo.getUsersByRole("NORMAL");
//		l.forEach( i -> System.out.println(i));
	}

}
