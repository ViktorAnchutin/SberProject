package com.vanchutin.event;

import org.springframework.stereotype.Component;

@Component
public class EventFactory {
    public Event getEvent(EventType type, int deviceId, int componentId){

        Event event = null;

        switch(type){
            case ERROR:
                event = new ErrorEvent(deviceId, componentId);
                break;
            case RESTORE:
                event = new RestoreEvent(deviceId, componentId);
                break;
            default:
                throw new IllegalArgumentException("Wrong event type!");
        }

        return event;
    }
}
