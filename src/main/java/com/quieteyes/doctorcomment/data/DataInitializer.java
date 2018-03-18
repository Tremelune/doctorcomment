package com.quieteyes.doctorcomment.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Doctor;

@Component
public class DataInitializer {
  private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

  private final CommentRepository commentRepository;
  private final DoctorRepository doctorRepository;


  DataInitializer(CommentRepository commentRepository, DoctorRepository doctorRepository) {
    this.commentRepository = commentRepository;
    this.doctorRepository = doctorRepository;
  }


  public void initialize() {
    log.info("Clearing database...");
    commentRepository.deleteAll();
    doctorRepository.deleteAll();

    log.info("Storing doctors...");
    doctorRepository.save(Doctor.create("music", "Feelgood"));
    doctorRepository.save(Doctor.create("music", "Dre"));
    doctorRepository.save(Doctor.create("medical", "Zizmore"));
  }
}
