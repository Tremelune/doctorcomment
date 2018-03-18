package com.quieteyes.doctorcomment.api;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.biz.CommentSaver;
import com.quieteyes.doctorcomment.biz.DoctorFinder;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

@RestController
@SuppressWarnings("unused") // Used by Spring.
@RequestMapping("/comments")
public class CommentController {
  private final CommentSaver commentSaver;
  private final DoctorFinder doctorFinder;


  CommentController(CommentSaver commentSaver, DoctorFinder doctorFinder) {
    this.commentSaver = commentSaver;
    this.doctorFinder = doctorFinder;
  }


  @RequestMapping(method = POST)
  public Iterable<Doctor> createComment(@RequestBody SaveCommentRequest req) {
    Doctor doc = doctorFinder.findById(req.doctorId).get();
    Comment comment = Comment.create(req.authorId, doc, req.body, req.rating);
    commentSaver.save(comment);
    return doctorFinder.findRecommended();
  }


  /**
   * I sometimes like to keep request/response structures distinct from the structure of models, because mirroring your
   * storage schema in your API can make things extremely difficult to change. It's also common that you'll want slight
   * differences in the two, as is the case here. The downside is manual translation of values.
   */
  public static class SaveCommentRequest {
    public Long authorId;
    public String body;
    public Integer rating;
    public Long doctorId;
  }
}
