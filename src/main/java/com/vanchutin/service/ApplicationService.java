package com.vanchutin.service;


import com.vanchutin.event.Event;
import com.vanchutin.service.processEvent.ProcessEventService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class ApplicationService {

    @Autowired @Setter
    private ProcessEventService eventService;
    @Autowired @Setter
    private DeviceUpdaterService deviceUpdater;

    public void processEvent(Event event){
            eventService.process(event);
            deviceUpdater.updateStatus(event.getDeviceId());
    }


}
