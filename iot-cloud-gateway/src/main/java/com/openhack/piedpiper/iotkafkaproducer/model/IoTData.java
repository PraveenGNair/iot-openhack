package com.openhack.piedpiper.iotkafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Class to represent the IoT vehicle data.
 * @author praveen.nair
 */
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

    public IoTData() {

    }

    public IoTData(String vehicleId, String vehicleType, String routeId, String latitude, String longitude, double temperature,
                   long assetCount, List<Asset> assetDetails, Date timestamp, double speed, double fuelLevel) {
        super();
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.routeId = routeId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.assetCount = assetCount;
        this.assetDetails = assetDetails;
        this.timestamp = timestamp;
        this.speed = speed;
        this.fuelLevel = fuelLevel;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getSpeed() {
        return speed;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public long getAssetCount() {
        return assetCount;
    }

    public List<Asset> getAssetDetails() {
        return assetDetails;
    }


    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setAssetCount(long assetCount) {
        this.assetCount = assetCount;
    }

    public void setAssetDetails(List<Asset> assetDetails) {
        this.assetDetails = assetDetails;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
}
