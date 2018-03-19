package com.quieteyes.doctorcomment.biz;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.DoctorRepository;
import com.quieteyes.doctorcomment.model.Doctor;

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
    // TRICK QUESTION! WE RECOMMEND THEM ALL! We could add a limit to the database query, sublist this list here, have
    // all kinds of logic to figure out why certain doctors are recommended...We could build a whole system for it.
    // That's gonna be a todo.
    return findAll();
  }
}
