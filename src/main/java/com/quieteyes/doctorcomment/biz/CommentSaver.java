package com.quieteyes.doctorcomment.biz;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.CommentRepository;
import com.quieteyes.doctorcomment.model.Comment;

/** All Comment persistence should go through this class. */
@Component
public class CommentSaver {
  private final CommentFinder commentFinder;
  private final CommentRepository commentRepository;


  CommentSaver(CommentFinder commentFinder, CommentRepository commentRepository) {
    this.commentFinder = commentFinder;
    this.commentRepository = commentRepository;
  }


  /**
   * Checks for validity and stores the comment if all is well.
   *
   * @throws IllegalArgumentException if:
   * - There is no author ID.
   * - There is no doctor.
   * - The body is empty.
   * - Rating isn't 1-5 (inclusive).
   */
  public void save(Comment comment) {
    validate(comment);
    commentRepository.save(comment);
  }


  public void update(Long commentId, String body, Integer rating) {
    Comment comment = getComment(commentId);

    // Only update what we have been provided.
    if (!isEmpty(body)) {
      comment.setBody(body);
    }

    if (rating != null) {
      comment.setRating(rating);
    }

    commentRepository.save(comment);
  }


  public void deactivate(Long commentId) {
    Comment comment = getComment(commentId);
    comment.setActive(false);
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


  private Comment getComment(Long id) {
    Optional<Comment> optional = commentFinder.findById(id);
    if (!optional.isPresent()) {
      throw new IllegalArgumentException("No Comment found with ID: " + id);
    }

    return optional.get();
  }
}
