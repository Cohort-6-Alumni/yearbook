package com.obsidi.yearbook.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private UUID profileId;

  private String bio;
  private String interests;
  private String hobbies;
  private String headline;

  @Column(name = "\"favoriteCodingSnack\"")
  private String favoriteCodingSnack;

  @Column(name = "\"favoriteQuote\"")
  private String favoriteQuote;

  @Column(name = "\"mostLikelyTo\"")
  private String mostLikelyTo;

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

  @Column(columnDefinition = "TEXT")
  private String picture;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "\"userId\"")
  private User user;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"createdOn\"")
  private Instant createdOn;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"updatedOn\"")
  private Timestamp updatedOn;

  public Timestamp getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Timestamp updatedOn) {
    this.updatedOn = updatedOn;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public Profile() {}

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

  public String getHeadline() {
    return headline;
  }

  public void setHeadline(String headline) {
    this.headline = headline;
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

  public String getMostLikelyTo() {
    return mostLikelyTo;
  }

  public void setMostLikelyTo(String mostLikelyTo) {
    this.mostLikelyTo = mostLikelyTo;
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
        + ", headline="
        + headline
        + ", favoriteCodingSnack="
        + favoriteCodingSnack
        + ", favoriteQuote="
        + favoriteQuote
        + ", mostLikelyTo="
        + mostLikelyTo
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
        + ", picture="
        + picture
        + ", user="
        + user
        + ", getProfileId()="
        + getProfileId()
        + ", getBio()="
        + getBio()
        + ", getInterests()="
        + getInterests()
        + ", getHobbies()="
        + getHobbies()
        + ", getHeadline()="
        + getHeadline()
        + ", getFavoriteCodingSnack()="
        + getFavoriteCodingSnack()
        + ", getFavoriteQuote()="
        + getFavoriteQuote()
        + ", getMostLikelyTo()="
        + getMostLikelyTo()
        + ", getMostMemorableBootcampMoment()="
        + getMostMemorableBootcampMoment()
        + ", getAdviceForFutureCohort()="
        + getAdviceForFutureCohort()
        + ", getBiggestChallenge()="
        + getBiggestChallenge()
        + ", getHowYouOvercameIt()="
        + getHowYouOvercameIt()
        + ", getLastWords()="
        + getLastWords()
        + ", getPicture()="
        + getPicture()
        + ", getUser()="
        + getUser()
        + ", getClass()="
        + getClass()
        + ", hashCode()="
        + hashCode()
        + ", toString()="
        + super.toString()
        + "]";
  }
}
