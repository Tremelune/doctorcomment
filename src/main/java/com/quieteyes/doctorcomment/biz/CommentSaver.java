package com.quieteyes.doctorcomment.biz;

import static com.quieteyes.doctorcomment.biz.SaveCommentValidationException.AUTHOR;
import static com.quieteyes.doctorcomment.biz.SaveCommentValidationException.BODY;
import static com.quieteyes.doctorcomment.biz.SaveCommentValidationException.DOCTOR;
import static com.quieteyes.doctorcomment.biz.SaveCommentValidationException.RATING;
import static org.springframework.util.StringUtils.isEmpty;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.CommentRepository;
import com.quieteyes.doctorcomment.model.Comment;

/** All Comment persistence should go through this class. */
@Component
public class CommentSaver {
  private final CommentRepository commentRepository;


  CommentSaver(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }


  /**
   * Checks for valid comments and stores the comment with timestamp of now if all is well.
   *
   * @throws IllegalArgumentException if:
   * - THere is no author ID.
   * - There is no doctor.
   * - The body is empty.
   * - Rating isn't 1-5 (inclusive).
   */
  public void save(Comment comment) throws SaveCommentValidationException {
    validate(comment);
    comment.setCreatedOn(LocalDate.now());
    commentRepository.save(comment);
  }

  private void validate(Comment comment) throws SaveCommentValidationException {
    if(comment.getAuthorId() == null) {
      throw AUTHOR;
    }

    if(comment.getDoctor() == null) {
      throw DOCTOR;
    }

    if (isEmpty(comment.getBody())) {
      throw BODY;
    }

    if(comment.getRating() == null || comment.getRating() < 1 || comment.getRating() > 5) {
      throw RATING;
    }
  }
}
