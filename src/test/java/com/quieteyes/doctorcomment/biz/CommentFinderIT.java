package com.quieteyes.doctorcomment.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.UUID;

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
public class CommentFinderIT {
  @Autowired
  private CommentFinder underTest;
  @Autowired
  private CommentSaver commentSaver;
  @Autowired
  private DoctorFinder doctorFinder;
  @Autowired
  private DataInitializer dataInitializer;


  @Before
  public void setup() {
    dataInitializer.initialize();
  }


  @Test
  public void testFindById() throws Exception {
    Doctor doc = doctorFinder.findAll().iterator().next();
    String body = UUID.randomUUID().toString(); // Make sure we're not pulling some other record...
    Comment comment = Comment.create(762L, doc, body, 2);
    commentSaver.save(comment);

    Optional<Comment> commentOptional = underTest.findById(comment.getId());

    assertTrue(commentOptional.isPresent());
    Comment found = commentOptional.get();
    assertEquals(found.getBody(), body);
  }
}
