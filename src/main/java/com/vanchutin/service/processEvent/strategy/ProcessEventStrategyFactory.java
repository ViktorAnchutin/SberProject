package com.vanchutin.service.processEvent.strategy;

import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessEventStrategyFactory {

    @Autowired
    ProcessErrorStrategy processErrorStrategy;

    @Autowired
    ProcessRestoreStrategy processRestoreStrategy;

    public ProcessEventStrategy getProcessStrategy(Event event){
        if(event instanceof ErrorEvent)
            return processErrorStrategy;
        else if(event instanceof RestoreEvent)
            return processRestoreStrategy;
        else
            throw new IllegalArgumentException("Strategy for this event is not implemented");
    }
}
