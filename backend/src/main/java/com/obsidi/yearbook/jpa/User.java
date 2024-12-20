package com.obsidi.yearbook.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  // @Id
  // @ColumnDefault("gen_random_uuid()")
  // @Column(name = "\"userId\"", nullable = false)
  // @JsonProperty(access = Access.WRITE_ONLY)
  // private UUID userId;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID) // Hibernate's built-in UUID generator
  @Column(name = "\"userId\"", nullable = false, updatable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private UUID userId;

  @Column(name = "\"firstName\"")
  private String firstName;

  @Column(name = "\"lastName\"")
  private String lastName;

  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  private String role;

  @Column(name = "\"emailId\"")
  private String emailId;

  @Column(name = "\"createdOn\"")
  private Timestamp createdOn;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties({"user"})
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Profile profile;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"updatedOn\"")
  private Timestamp updatedOn;

  public Timestamp getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Timestamp updatedOn) {
    this.updatedOn = updatedOn;
  }

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
        + ", role="
        + role
        + ", emailId="
        + emailId
        + ", createdOn="
        + createdOn
        + "]";
  }
}
