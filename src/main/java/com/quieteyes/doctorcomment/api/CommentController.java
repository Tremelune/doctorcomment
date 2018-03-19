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


  /**
   * Creates a comment on a particular doctor. The request body should be JSON that matches the structure of
   * SaveRequest. All params are required.
   * - authorId: ID of user making the comment.
   * - body: Actual comment.
   * - rating: Rating of the doctor (1-5, inclusive).
   * - doctorId: ID of the doctor being commented on.
   */
  @RequestMapping(method = POST)
  public Iterable<Doctor> create(@RequestBody SaveRequest req) {
    Doctor doc = doctorFinder.findById(req.doctorId).get();
    Comment comment = Comment.create(req.authorId, doc, req.body, req.rating);
    commentSaver.save(comment);
    return doctorFinder.findRecommended();
  }


  /** Retrieves a comment by ID. */
  @RequestMapping(method = GET, value = "/{id}")
  public Comment get(@PathVariable("id") Long id) {
    return commentFinder.findById(id).get();
  }


  /**
   * Updates the body and/or rating of the comment. The request body should be JSON that matches the structure of
   * UpdateRequest. Null values are ignored.
   */
  @RequestMapping(method = PUT, value = "/{id}")
  public Comment update(@PathVariable("id") Long id, @RequestBody UpdateRequest req) {
    commentSaver.update(id, req.body, req.rating);
    return commentFinder.findById(id).get();
  }


  /**
   * Deactivates a comment.
   *
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
    public String body;
    public Integer rating;
  }
}
