package com.vanchutin.service.processEvent.strategy;

import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ProcessEventStrategyFactory {

    @Autowired
    ApplicationContext applicationContext;

    public ProcessEventStrategy getProcessStrategy(Event event){
        if(event instanceof ErrorEvent)
            return applicationContext.getBean(ProcessErrorStrategy.class);
        else if(event instanceof RestoreEvent)
            return applicationContext.getBean(ProcessRestoreStrategy.class);
        else
            throw new IllegalArgumentException("Strategy for this event is not implemented");
    }
}
