package com.quieteyes.doctorcomment.biz;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.DoctorDao;
import com.quieteyes.doctorcomment.model.Doctor;

/**
 * This is a 'tweener for the data layer. It has zero knowledge about how objects are persisted, and it is unencumbered
 * by any framework. This is where the business logic beyond simple CRUD operations would live.
 */
@Component
public class DoctorFinder {
  private final DoctorDao doctorDao;

  DoctorFinder(DoctorDao doctorDao) {
    this.doctorDao = doctorDao;
  }


  public Iterable<Doctor> findAll() {
    return doctorDao.getAll();
  }
}
