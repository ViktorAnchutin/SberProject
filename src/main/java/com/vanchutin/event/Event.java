package com.vanchutin.event;

public abstract class Event {
    protected Integer deviceId;
    protected Integer componentId;

    public abstract Integer getDeviceId() ;

    public abstract void setDeviceId(Integer deviceId) ;

    public abstract Integer getComponentId() ;

    public abstract void setComponentId(Integer componentId) ;

    @Override
    public String toString() {
        return String.format("Event from device id: %d, component id: %d", deviceId, componentId);
    }
}
