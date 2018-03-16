package com.quieteyes.doctorcomment.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.biz.DoctorFinder;
import com.quieteyes.doctorcomment.model.Doctor;

@RestController
@SuppressWarnings("unused") // Used by Spring.
public class DoctorController {
  private final DoctorFinder doctorFinder;


  DoctorController(DoctorFinder doctorFinder) {
    this.doctorFinder = doctorFinder;
  }


  @RequestMapping("/doctors")
  public Iterable<Doctor> getDoctors() {
    return doctorFinder.findAll();
  }
}
