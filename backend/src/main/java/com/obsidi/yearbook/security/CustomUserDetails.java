package com.obsidi.yearbook.security;

import com.obsidi.yearbook.jpa.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
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

    return null;
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
