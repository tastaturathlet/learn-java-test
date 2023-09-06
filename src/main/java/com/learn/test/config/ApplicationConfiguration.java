package com.learn.test.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplicationConfiguration {

  private static ApplicationConfiguration instance;

  public static ApplicationConfiguration getInstance() {
    if (instance == null) {
      instance = new ApplicationConfiguration();
    }
    return instance;
  }

  private String sparkMaster;

  private int sparkPartition = 1;

  private int sparkNumberPartition = 1;

  private String sparkEventLogDir = "tmp/spark_events";

  private Boolean sparkEventLogEnable = false;

  private boolean firstLoad = true;

}
