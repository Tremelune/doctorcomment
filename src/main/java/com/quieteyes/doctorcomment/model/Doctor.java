package com.quieteyes.doctorcomment.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors") // Explicitly defining table names avoids coincidental changes that destroy the world.
public class Doctor {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private String groupId; // Is this enumerable? If so...should be an enumeration.
  private String name;
  private String address; // Parsing and storing global addresses is its own blog post...
  private Double longitude;
  private Double latitude;
  //todo Specialties.


  // Persistent models get unwieldy quickly, so it's wise to make it explicit which parameters are required and which
  // are optional...Factory methods help with that.
  public static Doctor create(String groupId, String name, Location location) {
    Doctor doc = new Doctor();
    doc.setGroupId(groupId);
    doc.setName(name);
    doc.setLocation(location);
    return doc;
  }


  @SuppressWarnings("unused") // Used by JPA.
  protected Doctor() {
  }


  // Some Java ORM hoops...I want the location info persisted flat, but manipulated as a convenient object.
  public Location getLocation() {
    return new Location(address, longitude, latitude);
  }

  public void setLocation(Location location) {
    address = location.getAddress();
    longitude = location.getLongitude();
    latitude = location.getLatitude();
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
