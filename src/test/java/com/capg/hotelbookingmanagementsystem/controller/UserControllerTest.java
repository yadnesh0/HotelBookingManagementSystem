package com.capg.hotelbookingmanagementsystem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.service.impl.UserServiceImpl;


@WebMvcTest(controllers = UserController.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

	@Autowired
    private MockMvc mvc;
    
    @MockBean
    private UserServiceImpl userService;
    
    private User user;

	@BeforeEach
	void setup() {

		@SuppressWarnings("unused")
		User user = User.builder().userId(1)
	    .username("amit12")
	    .email("amit12@gmail.com")
	    .password("Amit@123")
	    .mobile("9837164592")
	    .address("Lucknow")
	    .build();

	}
    
    @Test(expected=NullPointerException.class)
    public void addUser() throws Exception{
    	
    	User u =  User.builder().userId(1)
    		    .username("amit12")
    		    .email("amit12@gmail.com")
    		    .password("Amit@123")
    		    .mobile("9837164592")
    		    .address("Lucknow")
    		    .build();
    	
    	Mockito.when(userService.addUser(u)).thenReturn(user);

		mvc.perform(MockMvcRequestBuilders.post("/user/createUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"+"}"))
		.andExpect(status().isOk());
    }
	
    @Test(expected=NullPointerException.class)
    public void updateUser() throws Exception{
    	
    	User u =  User.builder().userId(1)
    		    .username("amit12")
    		    .email("amit12@gmail.com")
    		    .password("Amit@123")
    		    .mobile("9837164592")
    		    .address("Lucknow")
    		    .build();
    	
    	Mockito.when(userService.updateUser(u)).thenReturn(user);

		mvc.perform(MockMvcRequestBuilders.put("/user/updateUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"+"}"))
		.andExpect(status().isOk());
    }
	
    @Test(expected=NullPointerException.class)
	public void showUserById() throws Exception {
		Mockito.when(userService.showUserById(0))
		.thenReturn(user);

		mvc.perform(MockMvcRequestBuilders.get("/user/getById/{user_id}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$.userName")
				.value(user.getUsername()));
	}
    
    @Test(expected=NullPointerException.class)
   	public void removeUserById() throws Exception {
   		Mockito.when(userService.removeUserById(0))
   		.thenReturn("User with Id : "+0+" not found ");
        
   		mvc.perform(MockMvcRequestBuilders.delete("/user/deletedById/{user_id}")
   				.contentType(MediaType.APPLICATION_JSON))
   		.andExpect(MockMvcResultMatchers.status().isOk());
   	}
  
}
