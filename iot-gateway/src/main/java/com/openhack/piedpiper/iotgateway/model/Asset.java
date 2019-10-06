package com.openhack.piedpiper.iotgateway.model;

import java.io.Serializable;

/**
 * Created by praveen.nair on 25/2/18.
 */
public class Asset implements Serializable{

    private String assestId;
    private long height;
    private long width;
    private long weight;
    private long temperature;
    private String location;

    public String getAssestId() {
        return assestId;
    }

    public void setAssestId(String assestId) {
        this.assestId = assestId;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
