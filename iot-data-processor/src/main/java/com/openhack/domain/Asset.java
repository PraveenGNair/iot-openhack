package com.openhack.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by praveen.nair on 2/19/2018.
 */
@Data
public class Asset implements Serializable {

  private String assestId;
  private long height;
  private long width;
  private long weight;
  private long temperature;
  private String location;

  @Override
  public String toString() {
    return "{" +
            "assestId:'" + assestId + '\'' +
            ", height:" + height +
            ", width:" + width +
            ", weight:" + weight +
            ", temperature:" + temperature +
            ", location:'" + location + '\'' +
            '}';
  }
}

