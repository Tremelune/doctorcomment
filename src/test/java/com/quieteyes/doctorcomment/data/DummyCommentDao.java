package com.quieteyes.doctorcomment.data;

import com.quieteyes.doctorcomment.model.Comment;

/**
 * I would normally put dummy classes inside the test class, but putting it here allows us to keep the *Repository
 * classes package-private (which is nice, because nothing but DAOs should use them). This is just a bit simpler than a
 * lot of hoops real mocking frameworks make you go through.
 */
public class DummyCommentDao extends CommentDao {
  public DummyCommentDao() {
    super(null);
  }


  @Override
  public void save(Comment comment) {
  }
}
