package com.quieteyes.doctorcomment.model;

/** Just a convenient wrapper for location info. */
public class Location {
  private String address; // Parsing and storing global addresses is its own blog post...
  private Double longitude;
  private Double latitude;


  public Location(String address, Double longitude, Double latitude) {
    this.address = address;
    this.longitude = longitude;
    this.latitude = latitude;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
}
