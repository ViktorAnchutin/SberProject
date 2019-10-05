package com.vanchutin.service;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventService {

    @Autowired
    private ComponentDao componentDao;

    public void process(Event event){
        if(event instanceof ErrorEvent){
            processErrorEvent((ErrorEvent) event);
        }
        else if(event instanceof RestoreEvent){
            processRestoreEvent((RestoreEvent)event);
        }
    }

    private void processErrorEvent(ErrorEvent event){
        componentDao.setStatusFalse(event.getDeviceId(), event.getComponentId());
    }

    private void processRestoreEvent(RestoreEvent event){
       componentDao.setStatusTrue(event.getDeviceId(), event.getComponentId());
    }
}
