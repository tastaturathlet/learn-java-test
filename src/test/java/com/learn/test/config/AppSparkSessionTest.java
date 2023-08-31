package com.learn.test.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.spark.SparkException;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.Test;

class AppSparkSessionTest {


  @Test
  void getSparkSession() {
    ApplicationConfiguration config = ApplicationConfiguration.getInstance();
    config.setSparkMaster("local[*]");

    SparkSession spark = AppSparkSession.getSparkSession();
    assertEquals("3.3.2", spark.version());
    spark.close();
  }

  @Test
  void getSparkSessionFail() {
    Exception exception = assertThrows(SparkException.class, () -> {
      SparkSession spark = AppSparkSession.getSparkSession();
    });

    String expectedMessage = "A master URL must be set in your configuration";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}