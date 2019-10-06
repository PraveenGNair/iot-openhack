package com.openhack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by praveen.nair on 2/19/2018.
 */
@Data
public class IoTData implements Serializable {
  private String vehicleId;
  private String vehicleType;
  private String routeId;
  private String longitude;
  private String latitude;
  private double temperature;
  private long assetCount;
  private List<Asset> assetDetails;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "MST")
  private Date timestamp;
  private double speed;
  private double fuelLevel;
  private String startLocation;
  private String endLocation;
}
