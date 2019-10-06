package com.openhack.piedpiper.iotkafkaproducer.model;

import java.io.Serializable;

/**
 * @author praveen.nair
 */
public class Asset implements Serializable {

    private String assestId;
    private long height;
    private long width;
    private long weight;
    private long temperature;
    private String location;

    public Asset() {
    }

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

    public void setWeight(long wight) {
        this.weight = wight;
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

    public Asset(String assestId, long height, long width, long weight, long temperature, String location) {
        this.assestId = assestId;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.temperature = temperature;
        this.location = location;
    }
}
