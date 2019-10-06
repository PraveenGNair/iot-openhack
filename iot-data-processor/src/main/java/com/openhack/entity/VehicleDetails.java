package com.openhack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by praveen.nair on 2/23/2018.
 */
@Data
public class VehicleDetails {
  private String vehicleId;
  private String vehicleType;
  private String routeId;
  private String longitude;
  private String latitude;
  private double temperature;
  private long assetCount;
  private String assetDetails;
  private Date timestamp;
  private double speed;
  private double fuelLevel;
  private String startLocation;
  private String endLocation;
}
