package com.quieteyes.doctorcomment.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.model.Comment;

/** I want to use findAll() for tests, but there should be no such method in production code. */
@Component
public class TestCommentDao {
  @Autowired
  private CommentRepository commentRepository;


  public Iterable<Comment> findAll() {
    return commentRepository.findAll();
  }
}
