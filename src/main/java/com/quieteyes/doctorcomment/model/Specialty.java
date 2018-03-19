package com.quieteyes.doctorcomment.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialties") // Explicitly defining table names avoids coincidental changes that destroy the world.
public class Specialty {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private String name;


  // Persistent models get unwieldy quickly, so it's wise to make it explicit which parameters are required and which
  // are optional...Factory methods help with that.
  public static Specialty create(String name) {
    Specialty specialty = new Specialty();
    specialty.setName(name);
    return specialty;
  }


  @SuppressWarnings("unused") // Used by JPA.
  protected Specialty() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
