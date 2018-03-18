package com.quieteyes.doctorcomment.biz;

public class SaveCommentValidationException extends Exception {
  // These explanations are for internal engineers consuming our API, so we can expose them in the API response.
  static final SaveCommentValidationException AUTHOR =
      new SaveCommentValidationException("Comments must have an author ID!");

  static final SaveCommentValidationException BODY =
      new SaveCommentValidationException("Comment body cannot be empty!");

  static final SaveCommentValidationException DOCTOR =
      new SaveCommentValidationException("Comments must have a doctor ID!");

  static final SaveCommentValidationException RATING
      = new SaveCommentValidationException("Rating must be between 1 and 5!");


  private final String explanation;


  private SaveCommentValidationException(String explanation) {
    this.explanation = explanation;
  }


  public String getExplanation() {
    return explanation;
  }
}
