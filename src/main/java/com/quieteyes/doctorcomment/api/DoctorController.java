package com.quieteyes.doctorcomment.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.biz.DoctorFinder;
import com.quieteyes.doctorcomment.model.Doctor;

@RestController
@SuppressWarnings("unused") // Used by Spring.
@RequestMapping("/doctors")
public class DoctorController {
  private final DoctorFinder doctorFinder;


  DoctorController(DoctorFinder doctorFinder) {
    this.doctorFinder = doctorFinder;
  }


  @RequestMapping(method = GET)
  public Iterable<Doctor> getDoctors() {
    return doctorFinder.findAll();
  }
}
