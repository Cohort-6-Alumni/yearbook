package com.obsidi.yearbook.service;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.obsidi.yearbook.exception.domain.EmailExistException;
import com.obsidi.yearbook.exception.domain.UserNotFoundException;
import com.obsidi.yearbook.exception.domain.UsernameExistException;
import com.obsidi.yearbook.jpa.Profile;
import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.provider.ResourceProvider;
import com.obsidi.yearbook.repository.UserRepository;
import com.obsidi.yearbook.security.JwtService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

  final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private UserRepository userRepository;
  @Autowired PasswordEncoder passwordEncoder;

  @Autowired EmailService emailService;

  @Autowired AuthenticationManager authenticationManager;

  @Autowired JwtService jwtService;

  @Autowired ResourceProvider provider;

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

  private void validateUsernameAndEmail(String username, String emailId) {

    this.userRepository
        .findByUsername(username)
        .ifPresent(
            u -> {
              throw new UsernameExistException(
                  String.format("Username already exists, %s", u.getUsername()));
            });

    this.userRepository
        .findByEmailId(emailId)
        .ifPresent(
            u -> {
              throw new EmailExistException(
                  String.format("Email already exists, %s", u.getEmailId()));
            });
  }

  // Signup method
  public User signup(User user) {
    // Convert username and emailId to lowercase
    user.setUsername(user.getUsername().toLowerCase());
    user.setEmailId(user.getEmailId().toLowerCase());

    this.validateUsernameAndEmail(user.getUsername(), user.getEmailId());

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    // Set createdOn to the current timestamp
    user.setCreatedOn(Timestamp.from(Instant.now()));

    // Save the modified user object to the database
    this.userRepository.save(user);

    // Return the modified user object
    return user;
  }

  // supplier doesnt take anyvalue but returns a value while consumer consumes and supplies nothing
  private void updateValue(Supplier<String> getter, Consumer<String> setter) {

    Optional.ofNullable(getter.get())
        // .filter(StringUtils::hasText)
        .map(String::trim)
        .ifPresent(setter);
  }

  private void updatePassword(Supplier<String> getter, Consumer<String> setter) {

    Optional.ofNullable(getter.get())
        .filter(StringUtils::hasText)
        .map(this.passwordEncoder::encode)
        .ifPresent(setter);
  }

  private User updateUser(User user, User currentUser) {

    this.updateValue(user::getFirstName, currentUser::setFirstName);
    this.updateValue(user::getLastName, currentUser::setLastName);
    this.updateValue(user::getPhone, currentUser::setPhone);
    this.updateValue(user::getEmailId, currentUser::setEmailId);
    this.updatePassword(user::getPassword, currentUser::setPassword);

    return this.userRepository.save(currentUser);
  }

  public User updateUser(User user) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    /* Validates the new email if provided */
    this.userRepository
        .findByEmailId(user.getEmailId())
        .filter(u -> !u.getUsername().equals(username))
        .ifPresent(
            u -> {
              throw new EmailExistException(
                  String.format("Email already exists, %s", u.getEmailId()));
            });

    /* Get and Update User */
    return this.userRepository
        .findByUsername(username)
        .map(currentUser -> this.updateUser(user, currentUser))
        .orElseThrow(
            () -> new UserNotFoundException(String.format("Username doesn't exist, %s", username)));
  }

  private Authentication authenticate(String username, String password) {
    return this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
  }

  public User authenticate(User user) {

    /* Spring Security Authentication. */
    this.authenticate(user.getUsername(), user.getPassword());

    /* Get User from the DB. */
    Optional<User> usr = this.userRepository.findByUsername(user.getUsername());
    return usr.get();
    // return this.userRepository.findByUsername(user.getUsername());
  }

  public HttpHeaders generateJwtHeader(String username) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(
        AUTHORIZATION,
        this.jwtService.generateJwtToken(username, this.provider.getJwtExpiration()));

    return headers;
  }

  public void resetPassword(String password) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        this.userRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        String.format("Username doesn't exist, %s", username)));

    user.setPassword(this.passwordEncoder.encode(password));

    this.userRepository.save(user);
  }

  public User getUser() {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    /* Get User from the DB. */
    return this.userRepository
        .findByUsername(username)
        .orElseThrow(
            () -> new UserNotFoundException(String.format("Username doesn't exist, %s", username)));
  }

  public void sendResetPasswordEmail(String emailId) {

    Optional<User> opt = this.userRepository.findByEmailId(emailId);

    if (opt.isPresent()) {
      this.emailService.sendResetPasswordEmail(opt.get());
    } else {
      logger.debug("Email doesn't exist, {}", emailId);
    }
  }

  private User updateUserProfile(Profile profile, User user) {

    Profile currentProfile = user.getProfile();

    if (Optional.ofNullable(currentProfile).isPresent()) {

      this.updateValue(profile::getHeadline, currentProfile::setHeadline);
      this.updateValue(profile::getBio, currentProfile::setBio);
      this.updateValue(profile::getInterests, currentProfile::setInterests);
      this.updateValue(profile::getHobbies, currentProfile::setHobbies);
      this.updateValue(profile::getPicture, currentProfile::setPicture);
      this.updateValue(profile::getFavoriteCodingSnack, currentProfile::setFavoriteCodingSnack);
      this.updateValue(profile::getFavoriteQuote, currentProfile::setFavoriteQuote);
      this.updateValue(profile::getMostLikelyTo, currentProfile::setMostLikelyTo);
      this.updateValue(
          profile::getMostMemorableBootcampMoment, currentProfile::setMostMemorableBootcampMoment);
      this.updateValue(profile::getAdviceForFutureCohort, currentProfile::setAdviceForFutureCohort);
      this.updateValue(profile::getBiggestChallenge, currentProfile::setBiggestChallenge);
      this.updateValue(profile::getHowYouOvercameIt, currentProfile::setHowYouOvercameIt);
      this.updateValue(profile::getLastWords, currentProfile::setLastWords);

    } else {
      user.setProfile(profile);
      profile.setUser(user);
    }

    return this.userRepository.save(user);
  }

  public User updateUserProfile(Profile profile) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    /* Get and Update User */
    return this.userRepository
        .findByUsername(username)
        .map(user -> this.updateUserProfile(profile, user))
        .orElseThrow(
            () -> new UserNotFoundException(String.format("Username doesn't exist, %s", username)));
  }
}
