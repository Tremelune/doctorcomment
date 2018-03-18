package com.quieteyes.doctorcomment.api;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.quieteyes.doctorcomment.data.DataInitializer;
import com.quieteyes.doctorcomment.data.TestCommentDao;
import com.quieteyes.doctorcomment.model.Comment;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CommentControllerIT {
  @Autowired
  private DataInitializer dataInitializer;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private TestCommentDao testCommentDao;


  @Before
  public void setup() {
    dataInitializer.initialize();
  }


  @Test
  public void testSaveComment() throws Exception {
    MockHttpServletRequestBuilder post = post("/comments")
        .contentType(APPLICATION_JSON)
        .content("{\"id\":1,\"authorId\":2,\"body\":\"req body\",\"rating\":3}");

    mockMvc.perform(post)
        .andDo(print())
        .andExpect(status().isOk());
    //todo Doctors response

    Iterable<Comment> comments = testCommentDao.findAll();
    assertEquals(comments.iterator().next().getBody(), "req body");
  }
}