package com.quieteyes.doctorcomment.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.quieteyes.doctorcomment.data.DataInitializer;

/** This gets run on application startup, wipes the datastore, and then populates it. */
@Component
@SuppressWarnings("unused") // Used by Spring.
public class AppInitializer implements ApplicationRunner {
  private final DataInitializer dataInitializer;

  AppInitializer(DataInitializer dataInitializer) {
    this.dataInitializer = dataInitializer;
  }


  @Override
  public void run(ApplicationArguments args) {
    dataInitializer.initialize();
  }
}
