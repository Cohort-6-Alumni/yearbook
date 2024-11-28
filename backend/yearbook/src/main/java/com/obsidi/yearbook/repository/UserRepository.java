package com.obsidi.yearbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obsidi.yearbook.jpa.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//SELECT * FROM User WHERE username = ?
		Optional<User> findByUsername(String username);
		//SELECT * FROM User WHERE emailId = ?
		Optional<User> findByEmailId(String email);


}
