package com.vanchutin;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.dao.ComponentDaoImpl;
import com.vanchutin.dao.DeviceDao;
import com.vanchutin.dao.DeviceDaoImpl;
import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;
import com.vanchutin.model.Component;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import com.vanchutin.service.ApplicationService;
import com.vanchutin.service.DeviceUpdaterService;
import com.vanchutin.service.ProcessEventService;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Application {
    public static void main(String[] args)
    {

        // init application and inject dependencies
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.vanchutin.model");

        ComponentDao componentDao = new ComponentDaoImpl(emf);
        DeviceDao deviceDao = new DeviceDaoImpl(emf);

        ApplicationService service = new ApplicationService();
        ProcessEventService processEventService = new ProcessEventService(componentDao);
        DeviceUpdaterService deviceUpdaterService = new DeviceUpdaterService(deviceDao, componentDao);
        service.setEventService(processEventService);
        service.setUpdaterService(deviceUpdaterService);

        // create event queue
        Queue<Event> eventQueue = new LinkedList<Event>();
        eventQueue.add(new ErrorEvent(48, 49));
        eventQueue.add(new ErrorEvent(48, 50));
        eventQueue.add(new ErrorEvent(48, 51));
        eventQueue.add(new RestoreEvent(48, 51));
        eventQueue.add(new RestoreEvent(48, 49));
        eventQueue.add(new RestoreEvent(48, 50));
        // process event queue
        service.processEventQueue(eventQueue);
    }
}
