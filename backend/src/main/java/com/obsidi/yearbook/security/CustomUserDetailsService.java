package com.obsidi.yearbook.security;

import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> opt = this.userRepository.findByUsername(username);

    if (opt.isEmpty()) {
      throw new UsernameNotFoundException("Username not found: " + username);
    }

    return new CustomUserDetails(opt.get());
  }
}
