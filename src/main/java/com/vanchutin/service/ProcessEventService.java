package com.vanchutin.service;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;


public class ProcessEventService {

    private ComponentDao componentDao;

    public ProcessEventService(ComponentDao componentDao){
        this.componentDao = componentDao;
    }

    public void process(Event event){
        if(event instanceof ErrorEvent){
            processErrorEvent((ErrorEvent) event);
        }
        else if(event instanceof RestoreEvent){
            processRestoreEvent((RestoreEvent)event);
        }
    }

    private void processErrorEvent(ErrorEvent event){
        componentDao.updateStatus(event.getComponentId(), false);
    }

    private void processRestoreEvent(RestoreEvent event){
       componentDao.updateStatus(event.getComponentId(), true);
    }
}
