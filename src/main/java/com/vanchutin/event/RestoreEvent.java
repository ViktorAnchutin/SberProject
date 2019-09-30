package com.vanchutin.event;

public class RestoreEvent extends Event {

    public RestoreEvent(Integer deviceId, Integer componentId)
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
