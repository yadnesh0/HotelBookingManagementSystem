package com.capg.hotelbookingmanagementsystem.entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name="users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false)
	private int userId;
	
	@Column(unique = true, updatable = false)
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, message = "Username must contain 3 characters.")
	private String username;
	
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	
	@NotBlank(message = "Last Name is mandatory ")
	private String lastName;
	
	@Column(name = "email_address",unique = true,nullable = false)
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message="Password must contain a lowercase character, "
                                        + "a uppercase character and a digit, minimum length must be 6 characters")
	private String password;
	
	@NotBlank(message = "Mobile is mandatory")
	private String mobile;
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	@Builder.Default
	private boolean enabled = true;
	
	private String role;
	
	@Override
	@JsonDeserialize(using = CustomAuthorityDeserializer.class)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		set.add(new Authority(role));
		return set;
	}
		        
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return enabled;
	}
	
}
