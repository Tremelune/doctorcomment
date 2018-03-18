package com.quieteyes.doctorcomment.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.quieteyes.doctorcomment.data.DummyCommentDao;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

public class CommentSaverTest {
  private final Doctor doc = Doctor.create(null, null);
  private final CommentSaver underTest = new CommentSaver(new DummyCommentDao());


  @Test(expected = IllegalArgumentException.class)
  public void testValidationBadAuthorId() {
    Comment comment = Comment.create(0, null, null, 0);
    underTest.save(comment);
  }


  public void testValidationNullDoctor() {
    Comment comment = Comment.create(1, null, null, 0);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationEmptyBody() {
    Comment comment = Comment.create(1, doc, null, 0);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationRatingLow() {
    Comment comment = Comment.create(1, doc, "body", 0);
    underTest.save(comment);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testValidationRatingHigh() {
    Comment comment = Comment.create(1, doc, "body", 6);
    underTest.save(comment);
  }


  @Test
  public void testSave() {
    Comment comment = Comment.create(1, doc, "body", 3);
    assertNull(comment.getCreatedOn());
    underTest.save(comment);
    assertNotNull(comment.getCreatedOn());
  }
}
