package com.vanchutin.service;


import com.vanchutin.event.Event;
import java.util.Queue;

public class ApplicationService {

    private ProcessEventService eventService;
    private DeviceUpdaterService deviceUpdater;

    public void processEventQueue(Queue<Event> queue){
        queue.forEach(event-> {
            eventService.process(event);
            deviceUpdater.update(event.getDeviceId());
        });
    }

    public void setEventService(ProcessEventService eventService) {
        this.eventService = eventService;
    }

    public void setUpdaterService(DeviceUpdaterService deviceUpdater) {
        this.deviceUpdater = deviceUpdater;
    }
}
