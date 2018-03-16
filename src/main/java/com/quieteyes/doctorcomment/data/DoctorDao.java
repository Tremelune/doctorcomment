package com.quieteyes.doctorcomment.data;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Doctor;

/**
 * This operates as the gateway to the actual data accessor class. Its benefits are:
 *
 * 1) Not controlled by magic.
 * 2) Explicitly enumerates allowed methods, hiding the Spring Repository (which exposes such methods as deleteAll()).
 * 3) Allows for custom logic not possible in the Repository interface.
 */
@Component
public class DoctorDao {
  private final DoctorRepository doctorRepository;


  DoctorDao(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }


  /** Persist the doctor to the datastore. */
  public void save(Doctor doc) {
    doctorRepository.save(doc);
  }


  public Iterable<Doctor> getAll() {
    return doctorRepository.findAll();
  }
}
