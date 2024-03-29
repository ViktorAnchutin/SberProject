package com.vanchutin.event;

public class ErrorEvent extends Event {


    public ErrorEvent(){}
    public ErrorEvent(Integer deviceId, Integer componentId)
    {
        this.componentId = componentId;
        this.deviceId = deviceId;
    }

    public Integer getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

}
