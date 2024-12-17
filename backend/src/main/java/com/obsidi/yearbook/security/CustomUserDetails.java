package com.obsidi.yearbook.security;

import com.obsidi.yearbook.jpa.User;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  /** */
  private static final long serialVersionUID = 1L;

  private User user;

  // Constructor
  public CustomUserDetails(User user) {
    super();
    this.user = user;
  }

   @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Map the user's role to a GrantedAuthority
    return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
  }

  @Override
  public String getPassword() {

    return this.user.getPassword();
  }

  @Override
  public String getUsername() {

    return this.user.getUsername();
  }
}
