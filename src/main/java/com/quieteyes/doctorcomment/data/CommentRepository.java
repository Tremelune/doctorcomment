package com.quieteyes.doctorcomment.data;

import org.springframework.data.repository.CrudRepository;

import com.quieteyes.doctorcomment.model.Comment;

/** Magic Spring DAO. */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
