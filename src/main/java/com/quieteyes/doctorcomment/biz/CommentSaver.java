package com.quieteyes.doctorcomment.biz;

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
  public void save(Comment comment) {
    validate(comment);
    comment.setCreatedOn(LocalDate.now());
    commentRepository.save(comment);
  }

  private void validate(Comment comment) {
    // These explanations are for internal engineers consuming our API, so we can expose them in the API response.
    if(comment.getAuthorId() == null) {
      throw new IllegalArgumentException("Comments must have an author ID!");
    }

    if(comment.getDoctor() == null) {
      throw new IllegalArgumentException("Comments must have a doctor ID!");
    }

    if (isEmpty(comment.getBody())) {
      throw new IllegalArgumentException("Comment body cannot be empty!");
    }

    if(comment.getRating() == null || comment.getRating() < 1 || comment.getRating() > 5) {
      throw new IllegalArgumentException("Rating must be between 1 and 5!");
    }
  }
}
