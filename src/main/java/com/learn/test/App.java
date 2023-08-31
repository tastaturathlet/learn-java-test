package com.learn.test;

import com.learn.test.config.ApplicationConfiguration;
import com.learn.test.config.SetEnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    runApp();
  }

  static void runApp() {
    SetEnvironmentProperties setEnvironmentProperties = new SetEnvironmentProperties();
    setEnvironmentProperties.loadProperties();

    ApplicationConfiguration config = ApplicationConfiguration.getInstance();

    LOGGER.info("[APP] config: {}", config);
    
  }
}
