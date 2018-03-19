package com.quieteyes.doctorcomment.biz;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.DoctorRepository;
import com.quieteyes.doctorcomment.model.Doctor;

/**
 * This is a 'tweener for the data layer. It has zero knowledge about how objects are persisted, and it is unencumbered
 * by any framework or magic. This is where the business logic beyond simple CRUD operations would live.
 */
@Component
public class DoctorFinder {
  private final DoctorRepository doctorRepository;


  DoctorFinder(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }


  public Iterable<Doctor> findAll() {
    return doctorRepository.findAll();
  }

  public Optional<Doctor> findById(Long id) {
    return doctorRepository.findById(id);
  }

  public Iterable<Doctor> findRecommended() {
    // TRICK QUESTION WE RECOMMEND THEM ALL! We could add a limit to the database query, sublist this list here, have
    // all kinds of logic to figure out why certain doctors are recommended... todo
    return findAll();
  }
}
