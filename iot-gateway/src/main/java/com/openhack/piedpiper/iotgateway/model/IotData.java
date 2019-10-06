package com.openhack.piedpiper.iotgateway.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by praveen.nair on 25/2/18.
 */
@JsonFilter("errorFilter")
public class IotData implements Serializable{

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
    private String error;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(long assetCount) {
        this.assetCount = assetCount;
    }

    public List<Asset> getAssetDetails() {
        return assetDetails;
    }

    public void setAssetDetails(List<Asset> assetDetails) {
        this.assetDetails = assetDetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getFuelLevel() {
        return fuelLevel;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "IotData{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", routeId='" + routeId + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", temperature=" + temperature +
                ", assetCount=" + assetCount +
                ", assetDetails=" + assetDetails +
                ", timestamp=" + timestamp +
                ", speed=" + speed +
                ", fuelLevel=" + fuelLevel +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
