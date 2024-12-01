package com.obsidi.yearbook.controller;

import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.service.UserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private UserService userService;

  // List all users
  @GetMapping("/")
  public List<User> listUsers() {
    return userService.listUsers();
  }

  // Find user by username
  @GetMapping("/{username}")
  public Optional<User> findByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  // Create a new user
  @PostMapping("/{first}/{last}/{username}/{password}/{phone}/{emailId}")
  public User createUser(
      @PathVariable String first,
      @PathVariable String last,
      @PathVariable String username,
      @PathVariable String password,
      @PathVariable String phone,
      @PathVariable String emailId) {

    User user = new User();
    user.setFirstName(first);
    user.setLastName(last);
    user.setUsername(username);
    user.setPassword(password);
    user.setPhone(phone);
    user.setEmailId(emailId);

    return userService.createUser(user);
  }

  // Signup a new user
  @PostMapping("/signup")
  public User signup(@RequestBody User user) {
    // Log debug message for user signup
    logger.debug("Signing up, username: {}", user.getUsername());

    // Pass the user object to the signup method in UserService
    return this.userService.signup(user);
  }
}
