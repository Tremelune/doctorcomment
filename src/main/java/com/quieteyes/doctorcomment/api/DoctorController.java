package com.quieteyes.doctorcomment.api;

import static java.util.Collections.singletonList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.model.Doctor;

@RestController
@SuppressWarnings("unused") // Used by Spring.
public class DoctorController {
  @RequestMapping("/doctors")
  public Iterable<Doctor> getDoctors() {
    return singletonList(Doctor.create("love", "Feelgood"));
  }
}
