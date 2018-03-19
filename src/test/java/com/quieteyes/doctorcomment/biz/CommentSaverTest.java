package com.quieteyes.doctorcomment.biz;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.quieteyes.doctorcomment.data.CommentRepository;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;
import com.quieteyes.doctorcomment.model.Location;

/** Unit test. Fast; no magic dependencies. */
public class CommentSaverTest {
  private final CommentFinder commentFinder = mock(CommentFinder.class);
  private final CommentSaver underTest = new CommentSaver(commentFinder, mock(CommentRepository.class));
  private final Doctor doc = Doctor.create(null, null, new Location(null, null, null));


  @Test(expected = IllegalArgumentException.class)
  public void testValidationNullAuthorId() throws Exception {
    Comment comment = Comment.create(null, null, null, null);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationNullDoctor() throws Exception {
    Comment comment = Comment.create(1L, null, null, null);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationEmptyBody() throws Exception {
    Comment comment = Comment.create(1L, doc, null, null);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationRatingNull() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", null);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationRatingLow() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", 0);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationRatingHigh() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", 6);
    underTest.save(comment);
  }


  @Test
  public void testSave() {
    Comment comment = Comment.create(1L, doc, "body", 3);
    underTest.save(comment);
  }
}
