package com.quieteyes.doctorcomment.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.quieteyes.doctorcomment.data.DataInitializer;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DoctorControllerIT {
  @Autowired
  private DataInitializer dataInitializer;
  @Autowired
  private MockMvc mockMvc;


  @Before
  public void setup() {
    dataInitializer.initialize();
  }


  @Test
  public void testGetDoctors() throws Exception {
    mockMvc.perform(get("/doctors"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].name").value("Feelgood"))
        .andExpect(jsonPath("$.[0].location.address").value("14 Tomcat Alley, New York NY 11211"))
        .andExpect(jsonPath("$.[0].location.longitude").value(-73.935242))
        .andExpect(jsonPath("$.[0].location.latitude").value(40.730610))
        .andExpect(jsonPath("$.[0].specialties.[0].name").value("brain"))
        .andExpect(jsonPath("$.[0].specialties.[1].name").value("spin"));
  }
}
