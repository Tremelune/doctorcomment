package com.quieteyes.doctorcomment.biz;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quieteyes.doctorcomment.model.Doctor;

@Service
public class DoctorFinder {
  public List<Doctor> findAll() {
    // todo Pull from a database!
    return Arrays.asList(
        Doctor.create("music", "Feelgood"),
        Doctor.create("music", "Dre"),
        Doctor.create("medical", "Zizmore")
    );
  }
}
