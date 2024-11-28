package com.obsidi.yearbook.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.repository.UserRepository;



public class UserService {
	

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	// @Autowired
	// UserDao userDao;

	@Autowired
	UserRepository userRepository;
	/*
	 * @Autowired EmailService emailService;
	 * 
	 * @Autowired PasswordEncoder passwordEncoder;
	 * 
	 * @Autowired AuthenticationManager authenticationManager;
	 * 
	 * @Autowired JwtService jwtService;
	 * 
	 * @Autowired ResourceProvider provider;
	 */


	public Optional<User> findByUsername(String username) {
		// return this.userDao.findByUsername(username);
		return this.userRepository.findByUsername(username);
	}

	public void createUser(User user) {
		// this.userDao.createUser(user);
		this.userRepository.save(user);
	}

}
