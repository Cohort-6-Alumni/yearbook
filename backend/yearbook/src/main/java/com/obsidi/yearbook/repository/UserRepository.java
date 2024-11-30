package com.obsidi.yearbook.repository;

import com.obsidi.yearbook.jpa.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  // SELECT * FROM User WHERE username = ?
  Optional<User> findByUsername(String username);

  // SELECT * FROM User WHERE emailId = ?
  Optional<User> findByEmailId(String email);
}
