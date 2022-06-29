package com.capg.hotelbookingmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.hotelbookingmanagementsystem.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsernameAndPassword(String username, String password);
    
	public User findByUsername(String username);
    
	@Query("SELECT u FROM User u WHERE u.role= :n")
	public List<User> getUsersByRole(@Param("n") String role);
}
