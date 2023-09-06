package com.learn.test.config;

public class SetEnvironmentProperties {

  public static final String MASTER = "MASTER";
  public static final String PARTITIONS = "PARTITIONS";
  public static final String NUM_PARTITIONS = "NUM_PARTITIONS";
  public static final String EVENT_LOG_DIR = "EVENT_LOG_DIR";
  public static final String EVENT_LOG_ENABLE = "EVENT_LOG_ENABLE";

  public SetEnvironmentProperties() {
    // default constructor
  }

  public void loadProperties() {

    ApplicationConfiguration appConfiguration = ApplicationConfiguration.getInstance();
    if (System.getenv(MASTER) != null && !System.getenv(MASTER).isEmpty()) {
      appConfiguration.setSparkMaster(String.valueOf(System.getenv(MASTER)));
    }

    if (System.getenv(PARTITIONS) != null && !System.getenv(PARTITIONS).isEmpty()) {
      appConfiguration.setSparkPartition(Integer.valueOf(System.getenv(PARTITIONS)));
    }

    if (System.getenv(NUM_PARTITIONS) != null && !System.getenv(NUM_PARTITIONS).isEmpty()) {
      appConfiguration.setSparkNumberPartition(Integer.valueOf(System.getenv(NUM_PARTITIONS)));
    }

    if (System.getenv(EVENT_LOG_DIR) != null && !System.getenv(EVENT_LOG_DIR).isEmpty()) {
      appConfiguration.setSparkEventLogDir(String.valueOf(System.getenv(EVENT_LOG_DIR)));
    }

    if (System.getenv(EVENT_LOG_ENABLE) != null && !System.getenv(EVENT_LOG_ENABLE).isEmpty()) {
      appConfiguration.setSparkEventLogEnable(Boolean.valueOf(System.getenv(EVENT_LOG_ENABLE)));
    }
  }
}
