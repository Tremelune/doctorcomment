package com.quieteyes.doctorcomment.biz;

import static org.springframework.util.StringUtils.isEmpty;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.CommentDao;
import com.quieteyes.doctorcomment.model.Comment;

/** All Comment persistence should go through this class. */
@Component
public class CommentSaver {
  private final CommentDao commentDao;


  CommentSaver(CommentDao commentDao) {
    this.commentDao = commentDao;
  }


  /**
   * Checks for valid comments and stores the comment with timestamp of now if all is well.
   *
   * @throws IllegalArgumentException if there is no doctor, the body is empty, or if rating isn't 1-5 (inclusive).
   */
  public void save(Comment comment) {
    if(comment.getAuthorId() < 1) {
      throw new IllegalArgumentException("Invalid author ID.");
    }

    if(comment.getDoctor() == null) {
      throw new IllegalArgumentException("Comments must have an associated doctor.");
    }

    if (isEmpty(comment.getBody())) {
      throw new IllegalArgumentException("Body cannot be empty.");
    }

    if(comment.getRating() < 1 || comment.getRating() > 5) {
      throw new IllegalArgumentException("Rating must be between 1-5 (inclusive).");
    }

    comment.setCreatedOn(LocalDate.now());
    commentDao.save(comment);
  }
}
