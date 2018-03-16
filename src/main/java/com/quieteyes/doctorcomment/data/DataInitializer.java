package com.quieteyes.doctorcomment.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Doctor;

@Component
public class DataInitializer {
  private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

  private final DoctorDao doctorDao;
  private final DoctorRepository doctorRepository;


  DataInitializer(DoctorDao doctorDao, DoctorRepository doctorRepository) {
    this.doctorDao = doctorDao;
    this.doctorRepository = doctorRepository;
  }


  public void initialize() {
    log.info("Clearing database...");
    doctorRepository.deleteAll();

    log.info("Storing doctors...");
    doctorDao.save(Doctor.create("music", "Feelgood"));
    doctorDao.save(Doctor.create("music", "Dre"));
    doctorDao.save(Doctor.create("medical", "Zizmore"));
  }
}
