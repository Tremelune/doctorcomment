package com.quieteyes.doctorcomment.data;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Comment;

/**
 * This operates as the gateway to the actual data accessor class. Its benefits are:
 *
 * 1) Not controlled by magic.
 * 2) Explicitly enumerates allowed methods, hiding the Spring Repository (which exposes such methods as deleteAll()).
 * 3) Allows for custom logic not possible in the Repository interface.
 */
@Component
public class CommentDao {
  private final CommentRepository commentRepository;


  CommentDao(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }


  /** Persist the comment to the datastore. */
  public void save(Comment comment) {
    commentRepository.save(comment);
  }
}
