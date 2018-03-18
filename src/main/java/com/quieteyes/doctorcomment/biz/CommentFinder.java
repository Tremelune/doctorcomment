package com.quieteyes.doctorcomment.biz;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.CommentDao;
import com.quieteyes.doctorcomment.model.Comment;

/**
 * This is a 'tweener for the data layer. It has zero knowledge about how objects are persisted, and it is unencumbered
 * by any framework. This is where the business logic beyond simple CRUD operations would live.
 */
@Component
public class CommentFinder {
  private final CommentDao commentDao;

  CommentFinder(CommentDao commentDao) {
    this.commentDao = commentDao;
  }


  public Optional<Comment> findById(long id) {
    return commentDao.findById(id);
  }
}
