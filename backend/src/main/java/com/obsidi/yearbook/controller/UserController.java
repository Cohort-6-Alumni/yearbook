package com.obsidi.yearbook.controller;

import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.databind.JsonNode;
import com.obsidi.yearbook.jpa.Profile;
import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.service.UserService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(exposedHeaders = "Authorization")
@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private UserService userService;

  // List all users
  // @GetMapping("/")
  // public List<User> listUsers() {
  // return userService.listUsers();
  // }

  // Find user by username
  @GetMapping("/{username}")
  public Optional<User> findByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  // Endpoint to send an invite email
  @PostMapping("/invite")
  public void sendInviteEmail(@RequestBody JsonNode json) {
    String emailId = json.get("emailId").asText();
    logger.debug("Sending Invite Email, emailId: {}", emailId);

    this.userService.sendInvite(emailId);
  }

  // Endpoint to complete signup by setting a password
  @PostMapping("/signup/complete")
  public void completeSignup(@RequestBody JsonNode json) {
    String password = json.get("password").asText();

    logger.debug("Completing signup for user with password: {}", password);

    this.userService.completeSignup(password);
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User user) {

    logger.debug(
        "Authenticating, username: {}, password: {}", user.getUsername(), user.getPassword());

    /* Spring Security Authentication. */
    user = this.userService.authenticate(user);

    /* Generate JWT and HTTP Header */
    HttpHeaders jwtHeader = this.userService.generateJwtHeader(user.getUsername());

    logger.debug("User Authenticated, username: {}", user.getUsername());

    return new ResponseEntity<>(user, jwtHeader, OK);
  }

  @PostMapping("/update/profile")
  public User updateUserProfile(@RequestBody Profile profile) {

    logger.debug("Updating User Profile Data, Profile: {}", profile.toString());

    return this.userService.updateUserProfile(profile);
  }

  @GetMapping("/reset/{emailId}")
  public void sendResetPasswordEmail(@PathVariable String emailId) {

    logger.debug("Sending Reset Password Email, emailId: {}", emailId);

    this.userService.sendResetPasswordEmail(emailId);
  }

  @PostMapping("/reset")
  public void passwordReset(@RequestBody JsonNode json) {

    logger.debug("Resetting Password, password: {}", json.get("password").asText());

    this.userService.resetPassword(json.get("password").asText());
  }

  @GetMapping("/get")
  public User getUser() {

    logger.debug("Getting User Data");

    return this.userService.getUser();
  }

  @PostMapping("/update")
  public User updateUser(@RequestBody User user) {

    logger.debug("Updating User Data");

    return this.userService.updateUser(user);
  }

  @DeleteMapping("/delete")
  public void deleteUser() {
    logger.debug("Deleting user account.");
    this.userService.deleteUser();
  }
}
