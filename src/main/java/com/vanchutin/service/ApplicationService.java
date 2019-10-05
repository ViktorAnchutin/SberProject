package com.vanchutin.service;


import com.vanchutin.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class ApplicationService {

    @Autowired
    private ProcessEventService eventService;
    @Autowired
    private DeviceUpdaterService deviceUpdater;

    public void processEventQueue(Queue<Event> queue){
        queue.forEach(event-> {
            eventService.process(event);
            deviceUpdater.updateStatus(event.getDeviceId());
        });
    }


}
