package com.obsidi.yearbook.service;

import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.repository.UserRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private UserRepository userRepository;

  // Fetch all users
  public List<User> listUsers() {
    return this.userRepository.findAll();
  }

  // Find user by username
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  // Create a new user
  public User createUser(User user) {
    return this.userRepository.save(user);
  }

  // Signup method
  public User signup(User user) {
    // Convert username and emailId to lowercase
    user.setUsername(user.getUsername().toLowerCase());
    user.setEmailId(user.getEmailId().toLowerCase());

    // Set createdOn to the current timestamp
    user.setCreatedOn(Timestamp.from(Instant.now()));

    // Save the modified user object to the database
    this.userRepository.save(user);

    // Return the modified user object
    return user;
  }
}
