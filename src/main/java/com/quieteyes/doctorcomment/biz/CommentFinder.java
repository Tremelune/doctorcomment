package com.quieteyes.doctorcomment.biz;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.CommentRepository;
import com.quieteyes.doctorcomment.model.Comment;

/**
 * This is a 'tweener for the data layer. It has zero knowledge about how objects are persisted, and it is unencumbered
 * by any framework or magic. This is where the business logic beyond simple CRUD operations would live...if there were
 * additional logic to be done...
 */
@Component
public class CommentFinder {
  private final CommentRepository commentRepository;


  CommentFinder(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }


  public Optional<Comment> findById(Long id) {
    return commentRepository.findById(id);
  }
}
