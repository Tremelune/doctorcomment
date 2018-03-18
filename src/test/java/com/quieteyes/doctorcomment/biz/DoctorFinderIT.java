package com.quieteyes.doctorcomment.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quieteyes.doctorcomment.data.DataInitializer;
import com.quieteyes.doctorcomment.model.Doctor;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorFinderIT {
  @Autowired
  private CommentSaver commentSaver;
  @Autowired
  private DoctorFinder underTest;
  @Autowired
  private DataInitializer dataInitializer;


  @Before
  public void setup() {
    dataInitializer.initialize();
  }


  @Test
  public void testFindById() {
    Doctor doc = underTest.findAll().iterator().next();
    Optional<Doctor> found = underTest.findById(doc.getId());
    assertTrue(found.isPresent());
    assertEquals(found.get().getName(), doc.getName());
  }
}
