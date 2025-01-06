package com.obsidi.yearbook.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "\"Profile\"")
public class Profile implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"profileId\"", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID) // Use Hibernate's built-in strategy
  //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private UUID profileId;

  private String bio;
  private String interests;
  private String hobbies;
  private String instagram;

  @Column(name = "\"linkedIn\"")
  private String linkedIn;

  @Column(name = "\"favoriteCodingSnack\"")
  private String favoriteCodingSnack;

  @Column(name = "\"favoriteQuote\"")
  private String favoriteQuote;

  @Column(name = "\"currentRole\"")
  private String currentRole;

  @Column(name = "\"mostLikelyToQuestion\"")
  private String mostLikelyToQuestion;

  @Column(name = "\"mostLikelyToAnswer\"")
  private String mostLikelyToAnswer;

  @Column(name = "\"mostMemorableBootcampMoment\"")
  private String mostMemorableBootcampMoment;

  @Column(name = "\"adviceForFutureCohort\"")
  private String adviceForFutureCohort;

  @Column(name = "\"biggestChallenge\"")
  private String biggestChallenge;

  @Column(name = "\"howYouOvercameIt\"")
  private String howYouOvercameIt;

  @Column(name = "\"lastWords\"")
  private String lastWords;

  @Column(name = "\"previousField\"")
  private String previousField;

  @Column(columnDefinition = "TEXT")
  private String picture;

  @OneToOne
  @JsonBackReference
  @JoinColumn(name = "\"userId\"")
  private User user;

  @JsonProperty("firstName")
  public String getFirstName() {
    return user != null ? user.getFirstName() : null;
  }

  @JsonProperty("lastName")
  public String getLastName() {
    return user != null ? user.getLastName() : null;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"createdOn\"")
  private Timestamp createdOn;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"updatedOn\"")
  private Timestamp updatedOn;

  public UUID getProfileId() {
    return profileId;
  }

  public void setProfileId(UUID profileId) {
    this.profileId = profileId;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getInterests() {
    return interests;
  }

  public void setInterests(String interests) {
    this.interests = interests;
  }

  public String getHobbies() {
    return hobbies;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public String getLinkedIn() {
    return linkedIn;
  }

  public void setLinkedIn(String linkedIn) {
    this.linkedIn = linkedIn;
  }

  public String getFavoriteCodingSnack() {
    return favoriteCodingSnack;
  }

  public void setFavoriteCodingSnack(String favoriteCodingSnack) {
    this.favoriteCodingSnack = favoriteCodingSnack;
  }

  public String getFavoriteQuote() {
    return favoriteQuote;
  }

  public void setFavoriteQuote(String favoriteQuote) {
    this.favoriteQuote = favoriteQuote;
  }

  public String getCurrentRole() {
    return currentRole;
  }

  public void setCurrentRole(String currentRole) {
    this.currentRole = currentRole;
  }

  public String getMostLikelyToQuestion() {
    return mostLikelyToQuestion;
  }

  public void setMostLikelyToQuestion(String mostLikelyToQuestion) {
    this.mostLikelyToQuestion = mostLikelyToQuestion;
  }

  public String getMostLikelyToAnswer() {
    return mostLikelyToAnswer;
  }

  public void setMostLikelyToAnswer(String mostLikelyToAnswer) {
    this.mostLikelyToAnswer = mostLikelyToAnswer;
  }

  public String getMostMemorableBootcampMoment() {
    return mostMemorableBootcampMoment;
  }

  public void setMostMemorableBootcampMoment(String mostMemorableBootcampMoment) {
    this.mostMemorableBootcampMoment = mostMemorableBootcampMoment;
  }

  public String getAdviceForFutureCohort() {
    return adviceForFutureCohort;
  }

  public void setAdviceForFutureCohort(String adviceForFutureCohort) {
    this.adviceForFutureCohort = adviceForFutureCohort;
  }

  public String getBiggestChallenge() {
    return biggestChallenge;
  }

  public void setBiggestChallenge(String biggestChallenge) {
    this.biggestChallenge = biggestChallenge;
  }

  public String getHowYouOvercameIt() {
    return howYouOvercameIt;
  }

  public void setHowYouOvercameIt(String howYouOvercameIt) {
    this.howYouOvercameIt = howYouOvercameIt;
  }

  public String getLastWords() {
    return lastWords;
  }

  public void setLastWords(String lastWords) {
    this.lastWords = lastWords;
  }

  public String getPreviousField() {
    return previousField;
  }

  public void setPreviousField(String previousField) {
    this.previousField = previousField;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Timestamp createdOn) {
    this.createdOn = createdOn;
  }

  public Timestamp getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Timestamp updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public String toString() {
    return "Profile [profileId="
        + profileId
        + ", bio="
        + bio
        + ", interests="
        + interests
        + ", hobbies="
        + hobbies
        + ", instagram="
        + instagram
        + ", linkedIn="
        + linkedIn
        + ", favoriteCodingSnack="
        + favoriteCodingSnack
        + ", favoriteQuote="
        + favoriteQuote
        + ", currentRole="
        + currentRole
        + ", mostLikelyToQuestion="
        + mostLikelyToQuestion
        + ", mostLikelyToAnswer="
        + mostLikelyToAnswer
        + ", mostMemorableBootcampMoment="
        + mostMemorableBootcampMoment
        + ", adviceForFutureCohort="
        + adviceForFutureCohort
        + ", biggestChallenge="
        + biggestChallenge
        + ", howYouOvercameIt="
        + howYouOvercameIt
        + ", lastWords="
        + lastWords
        + ", previousField="
        + previousField
        + ", picture="
        + picture
        + ", user="
        + user
        + ", createdOn="
        + createdOn
        + ", updatedOn="
        + updatedOn
        + "]";
  }
}
