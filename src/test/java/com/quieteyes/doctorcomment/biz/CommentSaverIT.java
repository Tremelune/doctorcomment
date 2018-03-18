package com.quieteyes.doctorcomment.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quieteyes.doctorcomment.data.DataInitializer;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

/** Hits the actual database with all dependencies. Switchy validation logic is better handled in CommentSaverTest */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentSaverIT {
  @Autowired
  private CommentFinder commentFinder;
  @Autowired
  private CommentSaver underTest;
  @Autowired
  private DoctorFinder doctorFinder;
  @Autowired
  private DataInitializer dataInitializer;


  @Before
  public void setup() {
    dataInitializer.initialize();
  }


  @Test
  public void testSave() {
    Doctor doc = doctorFinder.findAll().iterator().next();
    Comment comment = Comment.create(762L, doc, "He's the one they call Dr Feelgood", 2);
    underTest.save(comment);

    Optional<Comment> commentOptional = commentFinder.findById(comment.getId());

    assertTrue(commentOptional.isPresent());
    Comment found = commentOptional.get();
    assertEquals(found.getAuthorId(), 762);
    assertEquals(found.getBody(), "He's the one they call Dr Feelgood");
    assertNotNull(found.getCreatedOn());
    assertEquals(found.getRating(), 2);
    assertEquals(found.getDoctor().getId(), doc.getId());
  }
}
