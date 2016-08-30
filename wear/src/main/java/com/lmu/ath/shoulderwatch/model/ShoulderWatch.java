package com.lmu.ath.shoulderwatch.model;

/**
 * Created by daniela on 24.07.16.
 */
public class ShoulderWatch {

    private String time;
    private String locationX;
    private String locationY;
    private String environment;
    private String devicetype;
    private String deviceAnalog;
    private String deviceDigital;
    private String content;
    private String surfTime;
    private String surfRating;
    private String surfDistance;
    private String relativePosition;
    private String crowdDensity;
    private String defenceLevel;


    public void setTime(String time) {
        this.time = time;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public void setDeviceAnalog(String deviceAnalog) {
        this.deviceAnalog = deviceAnalog;
    }

    public void setDeviceDigital(String deviceDigital) {
        this.deviceDigital = deviceDigital;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSurfTime(String surfTime) {
        this.surfTime = surfTime;
    }

    public void setSurfRating(String surfRating) {
        this.surfRating = surfRating;
    }

    public void setSurfDistance(String surfDistance) {
        this.surfDistance = surfDistance;
    }

    public void setRelativePosition(String relativePosition) {
        this.relativePosition = relativePosition;
    }

    public void setCrowdDensity(String crowdDensity) {
        this.crowdDensity = crowdDensity;
    }

    public void setDefenceLevel(String defenceLevel) {
        this.defenceLevel = defenceLevel;
    }
}
