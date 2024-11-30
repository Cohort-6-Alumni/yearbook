package com.obsidi.yearbook.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
// import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @ColumnDefault("gen_random_uuid()")
  @Column(name = "\"userId\"", nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID userId;

  @Column(name = "\"firstName\"")
  private String firstName;

  @Column(name = "\"lastName\"")
  private String lastName;

  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  private String phone;

  private String role;

  @Column(name = "\"emailId\"")
  private String emailId;

  @Column(name = "\"createdOn\"")
  private Timestamp createdOn;

  @Column(name = "\"emailVerified\"")
  private boolean emailVerified;

  @JsonInclude(Include.NON_NULL)
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Profile profile;

  public User() {}

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public Timestamp getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Timestamp createdOn) {
    this.createdOn = createdOn;
  }
  
  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  @Override
  public String toString() {
    return "User [userId="
        + userId
        + ", firstName="
        + firstName
        + ", lastName="
        + lastName
        + ", username="
        + username
        + ", password="
        + password
        + ", phone="
        + phone
        + ", role="
        + role
        + ", emailId="
        + emailId
        + ", createdOn="
        + createdOn
        + "]";
  }
}
