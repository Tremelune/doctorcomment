package com.quieteyes.doctorcomment.api;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.biz.CommentFinder;
import com.quieteyes.doctorcomment.biz.CommentSaver;
import com.quieteyes.doctorcomment.biz.DoctorFinder;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

@RestController
@SuppressWarnings("unused") // Used by Spring.
@RequestMapping("/comments")
public class CommentController {
  private final CommentFinder commentFinder;
  private final CommentSaver commentSaver;
  private final DoctorFinder doctorFinder;


  CommentController(CommentFinder commentFinder, CommentSaver commentSaver, DoctorFinder doctorFinder) {
    this.commentFinder = commentFinder;
    this.commentSaver = commentSaver;
    this.doctorFinder = doctorFinder;
  }


  // todo Validation
  @RequestMapping(method = POST)
  public ResponseEntity<?> createComment(@RequestBody SaveCommentRequest req) {
    Doctor doc = doctorFinder.findAll().iterator().next(); // todo Find by ID.
    Comment comment = Comment.create(req.authorId, doc, req.body, req.rating);
    commentSaver.save(comment);
    return ResponseEntity.ok().build();
  }


  /**
   * I sometimes like to keep request/response structures distinct from the structure of models, because tying the
   * structure of your API to the structure of your persistent data is folly—it complicates changes to an extreme
   * degree. As such, wrapper/adapter requests can be handy, and puts the expected structure of the request right where
   * you're usually looking for it. The downside is manual translation of values.
   *
   * It's worth noting that this need not be done until an API must change, and it's possible that it might be moot
   * depending on the API versioning strategy. It's also worth noting that this probably is never worth it for internal
   * APIs used by other services, as opposed to the Public At Large (who will burn you on Twitter for breaking an
   * endpoint that was deprecated six years ago).
   */
  public static class SaveCommentRequest {
    public Long id;
    public Long authorId;
    public String body;
    public Integer rating;
    public Long doctorId;
  }
}
