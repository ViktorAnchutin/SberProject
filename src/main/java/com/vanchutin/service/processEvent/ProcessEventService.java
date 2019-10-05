package com.vanchutin.service.processEvent;

import com.vanchutin.event.Event;
import com.vanchutin.service.processEvent.strategy.ProcessEventStrategyFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventService {

    @Autowired
    ProcessEventStrategyFactory processEventStrategyFactory;

    public void process(Event event){
        processEventStrategyFactory.getProcessStrategy(event).process(event);
    }
}