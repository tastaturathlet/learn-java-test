package com.learn.test.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationConfigurationTest {

  ApplicationConfiguration config;

  @BeforeEach
  public void setupEach() {
    config = ApplicationConfiguration.getInstance();
  }


  @Test
  void getSparkMaster() {
    assertNull(config.getSparkMaster());
  }

  @Test
  void getSparkPartition() {
    assertEquals(1, config.getSparkPartition());
  }

  @Test
  void getSparkNumberPartition() {
    assertEquals(1, config.getSparkNumberPartition());
  }

  @Test
  void getSparkEventLogDir() {
    assertEquals("tmp/spark_events", config.getSparkEventLogDir());
  }

  @Test
  void getSparkEventLogEnable() {
    assertFalse(config.getSparkEventLogEnable());
  }

}