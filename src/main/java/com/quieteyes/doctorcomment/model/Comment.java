package com.quieteyes.doctorcomment.model;

import static javax.persistence.GenerationType.AUTO;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * All the builders, factory methods, and hidden constructors slow changes down a bit, so I don't usually bother until
 * functionality is fairly stable. The long-term benefits are real and worth it as things grow, however.
 */
@Entity
@Table(name = "comments") // Explicitly defining table names avoids coincidental changes that destroy the world.
public class Comment {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private long authorId; // This would probably be a join in the real world.
  private String body;
  private LocalDate createdOn;
  private int rating; // 1-5

  @ManyToOne
  private Doctor doctor;


  public static Comment create(long authorId, Doctor doc, String body, int rating) {
    return new Builder()
        .setAuthorId(authorId)
        .setDoctor(doc)
        .setBody(body)
        .setRating(rating)
        .build();
  }


  @SuppressWarnings("unused") // Used by JPA.
  protected Comment() {
  }

  private Comment(Long id, long authorId, Doctor doctor, String body, LocalDate createdOn, int rating) {
    this.id = id;
    this.authorId = authorId;
    this.doctor = doctor;
    this.body = body;
    this.createdOn = createdOn;
    this.rating = rating;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDate getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDate createdOn) {
    this.createdOn = createdOn;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }


  public static class Builder {
    private Long id;
    private long authorId;
    private Doctor doctor;
    private String body;
    private LocalDate createdOn;
    private int rating;

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setAuthorId(long authorId) {
      this.authorId = authorId;
      return this;
    }

    public Builder setDoctor(Doctor doctor) {
      this.doctor = doctor;
      return this;
    }

    public Builder setBody(String body) {
      this.body = body;
      return this;
    }

    public Builder setCreatedOn(LocalDate createdOn) {
      this.createdOn = createdOn;
      return this;
    }

    public Builder setRating(int rating) {
      this.rating = rating;
      return this;
    }

    public Comment build() {
      return new Comment(id, authorId, doctor, body, createdOn, rating);
    }
  }
}
