package com.quieteyes.doctorcomment.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quieteyes.doctorcomment.biz.CommentFinder;
import com.quieteyes.doctorcomment.biz.CommentSaver;
import com.quieteyes.doctorcomment.biz.DoctorFinder;
import com.quieteyes.doctorcomment.model.Comment;
import com.quieteyes.doctorcomment.model.Doctor;

/** So like...there's no authentication here. Anyone can create slanderous comments... */
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


  @RequestMapping(method = POST)
  public Iterable<Doctor> create(@RequestBody SaveRequest req) {
    Doctor doc = doctorFinder.findById(req.doctorId).get();
    Comment comment = Comment.create(req.authorId, doc, req.body, req.rating);
    commentSaver.save(comment);
    return doctorFinder.findRecommended();
  }


  @RequestMapping(method = GET, value = "/{id}")
  public Comment get(@PathVariable("id") Long id) {
    return commentFinder.findById(id).get();
  }


  @RequestMapping(method = PUT)
  public Comment update(@RequestBody UpdateRequest req) {
    commentSaver.update(req.commentId, req.body, req.rating);
    return commentFinder.findById(req.commentId).get();
  }


  /**
   * I thought about using PATCH here, but there was no data to send for the update. For this case, I prefer using a
   * sub-resource.
   *
   * https://stackoverflow.com/questions/28459418/rest-api-put-vs-patch-with-real-life-examples
   *
   * https://softwareengineering.stackexchange.com/questions/323415/how-to-design-a-rest-api-to-handle-non-crud-operations
   */
  @RequestMapping(method = POST, value = "/{id}/deactivations")
  public Comment deactivate(@PathVariable("id") Long id) {
    commentSaver.deactivate(id);
    return commentFinder.findById(id).get();
  }


  /**
   * I sometimes like to keep request/response structures distinct from the structure of models, because mirroring your
   * storage schema in your API can make things extremely difficult to change. It's also common that you'll want slight
   * differences in the two, as is the case here. The downside is manual translation of values.
   */
  public static class SaveRequest {
    public Long authorId;
    public String body;
    public Integer rating;
    public Long doctorId;
  }

  public static class UpdateRequest {
    public Long commentId;
    public String body;
    public Integer rating;
  }
}
