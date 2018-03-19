package com.quieteyes.doctorcomment.data;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Doctor;
import com.quieteyes.doctorcomment.model.Location;
import com.quieteyes.doctorcomment.model.Specialty;

@Component
public class DataInitializer {
  private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

  private final CommentRepository commentRepository;
  private final DoctorRepository doctorRepository;
  private final SpecialtyRepository specialtyRepository;


  public DataInitializer(
      CommentRepository commentRepository, DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
    this.commentRepository = commentRepository;
    this.doctorRepository = doctorRepository;
    this.specialtyRepository = specialtyRepository;
  }


  public void initialize() {
    log.info("Clearing database...");
    commentRepository.deleteAll();
    doctorRepository.deleteAll();

    log.info("Storing specialties...");
    Specialty brain = Specialty.create("brain");
    Specialty spin = Specialty.create("spin");
    specialtyRepository.save(brain);
    specialtyRepository.save(spin);

    log.info("Storing doctors...");
    Location loc = new Location("14 Tomcat Alley, New York NY 11211", -73.935242,40.730610);

    Doctor a = Doctor.create("music", "Feelgood", loc);
    Doctor b = Doctor.create("music", "Dre", loc);
    Doctor c = Doctor.create("medical", "Zizmore", loc);

    a.setSpecialties(new HashSet<>(asList(brain, spin)));
    b.setSpecialties(new HashSet<>(singletonList(spin)));

    doctorRepository.save(a);
    doctorRepository.save(b);
    doctorRepository.save(c);
  }
}
