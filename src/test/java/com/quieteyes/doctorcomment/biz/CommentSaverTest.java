package com.quieteyes.doctorcomment.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.quieteyes.doctorcomment.data.CommentRepository;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

public class CommentSaverTest {
  private final Doctor doc = Doctor.create(null, null);
  private final CommentSaver underTest = new CommentSaver(Mockito.mock(CommentRepository.class));


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationNullAuthorId() throws Exception {
    Comment comment = Comment.create(null, null, null, null);
    underTest.save(comment);
  }


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationNullDoctor() throws Exception {
    Comment comment = Comment.create(1L, null, null, null);
    underTest.save(comment);
  }


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationEmptyBody() throws Exception {
    Comment comment = Comment.create(1L, doc, null, null);
    underTest.save(comment);
  }


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationRatingNull() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", null);
    underTest.save(comment);
  }


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationRatingLow() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", 0);
    underTest.save(comment);
  }


  @Test(expected = SaveCommentValidationException.class)
  public void testValidationRatingHigh() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", 6);
    underTest.save(comment);
  }


  @Test
  public void testSave() throws Exception {
    Comment comment = Comment.create(1L, doc, "body", 3);
    assertNull(comment.getCreatedOn());
    underTest.save(comment);
    assertNotNull(comment.getCreatedOn());
  }
}
