package com.quieteyes.doctorcomment.model;

public class Doctor {
  private String groupId;
  private String name;
  //todo Location info.
  //todo Specialties.


  // Persistent models get unwieldy quickly, so it's wise to make it explicit which parameters are required and which
  // are optional...Factory methods help with that.
  public static Doctor create(String groupId, String name) {
    return new Builder()
        .setGroupId(groupId)
        .setName(name)
        .build();
  }


  //
  Doctor(String groupId, String name) {
    this.groupId = groupId;
    this.name = name;
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


  // This provides an opportunity to validate.
  private static class Builder {
    private String groupId;
    private String name;

    private Builder setGroupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    private Builder setName(String name) {
      this.name = name;
      return this;
    }

    private Doctor build() {
      return new Doctor(groupId, name);
    }
  }
}
