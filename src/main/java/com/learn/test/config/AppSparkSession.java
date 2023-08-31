package com.learn.test.config;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;

public class AppSparkSession {

  public static final String APP_NAME = "IdpMetaDataSink";

  private AppSparkSession() {
    // private constructor to hide the implicit public one
  }

  /**
   * Create a Spark Session. Set Spark master for local development
   *
   * @return Spark Session
   */
  public static SparkSession getSparkSession() {

    ApplicationConfiguration config = ApplicationConfiguration.getInstance();
    SparkSession spark;

    Builder sparkBuilder = SparkSession.builder()
        .appName(APP_NAME)
        .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
        .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
        .config("spark.hadoop.fs.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFileSystem")
        .config("spark.hadoop.fs.AbstractFileSystem.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFS")
        .config("spark.hadoop.fs.gs.auth.type", "APPLICATION_DEFAULT")
        .config("spark.eventLog.enabled", config.getSparkEventLogEnable().toString())
        .config("spark.eventLog.dir", config.getSparkEventLogDir());

    if (config.getSparkMaster() != null && !config.getSparkMaster().isEmpty()) {
      sparkBuilder.master(config.getSparkMaster());

      // for local development
      if ("local[*]".equals(config.getSparkMaster())) {
        sparkBuilder.config("spark.local.dir", "sparkdir/");
      }
    }

    spark = sparkBuilder.getOrCreate();
    spark.sparkContext().setLogLevel("INFO");
    return spark;
  }
}
