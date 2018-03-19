package com.quieteyes.doctorcomment.model;

/**
 * Just a convenient wrapper for location info. This could be its own table, but it's probably not worth it unless we
 * expect to run queries for location info across tables. Might as well keep it simple in the meantime.
 */
public class Location {
  private String address;
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
