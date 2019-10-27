package com.vanchutin.listener;

import com.vanchutin.event.Event;
import com.vanchutin.event.EventFactory;
import com.vanchutin.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    EventFactory eventFactory;

    @Autowired
    MessageMapper messageMapper;

    public void receiveMessage(String messageJson){
        log.info(String.format("Message received: %s", messageJson));
        Optional<Message> message = messageMapper.map(messageJson);
        if(message.isPresent()) {
            Event event = eventFactory.getEvent(message.get().getEventType(),
                                                message.get().getDeviceId(),
                                                message.get().getComponentId());
            applicationService.processEvent(event);
        }
    }

}
