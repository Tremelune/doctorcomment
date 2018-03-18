package com.quieteyes.doctorcomment.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {
  @Autowired
  private MockMvc mockMvc;


  @Test
  public void testGetDoctors() throws Exception {
    mockMvc.perform(get("/doctors"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].name").value("Feelgood"));
  }
}
