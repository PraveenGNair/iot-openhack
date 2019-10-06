package com.openhack.utils;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by praveen.nair on 2/19/2018.
 */
@Configuration
public class ApplicationConfig {

  @Value("${app.name:processor}")
  private String appName;

  @Value("${master.uri:local}")
  private String masterUri;

  @Value("${spark.cassandra.connection.host}")
  private String cassandraHost;

  @Value("${spark.cassandra.connection.port}")
  private String cassandraPort;

  @Value("${spark.cassandra.connection.keep_alive_ms}")
  private String cassandraKeepAlive;

  @Value("${com.iot.app.spark.checkpoint.dir}")
  private String checkPointDir;

  @Bean(name = "SparkConfBean")
  public SparkConf sparkConf() {
    SparkConf sparkConf = new SparkConf()
        .setAppName(appName)
        .setMaster(masterUri)
        .set("spark.cassandra.connection.host", cassandraHost)
        .set("spark.cassandra.connection.port", cassandraPort)
        .set("spark.cassandra.connection.keep_alive_ms", cassandraKeepAlive);

    return sparkConf;
  }

  @Bean(name = "JavaStreamingBean")
  public JavaStreamingContext javaStreamingContext() {
    JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf(), Durations
        .seconds(5));
    //javaStreamingContext.checkpoint(checkPointDir);
    return javaStreamingContext;
  }

}
