package com.obsidi.yearbook.service;

import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

  final Logger logger = LoggerFactory.getLogger(this.getClass());
 

  @Autowired UserRepository userRepository;

  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  public void createUser(User user) {
    this.userRepository.save(user);
  }
}
