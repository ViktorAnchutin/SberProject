package com.vanchutin.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventFactory {

    public Event getEvent(String eventType, int deviceId, int componentId){

        Event event = null;

        switch(eventType){
            case EventType.ERROR:
                event = new ErrorEvent(deviceId, componentId);
                break;
            case EventType.RESTORE:
                event = new RestoreEvent(deviceId, componentId);
                break;
            default:
                throw new IllegalArgumentException("Wrong event type!");
        }

        return event;
    }
}
