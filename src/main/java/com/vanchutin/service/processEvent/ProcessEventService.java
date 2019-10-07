package com.vanchutin.service.processEvent;

import com.vanchutin.event.Event;
import com.vanchutin.service.processEvent.strategy.ProcessEventStrategy;
import com.vanchutin.service.processEvent.strategy.ProcessEventStrategyFactory;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventService {

    @Autowired @Setter
    private ProcessEventStrategyFactory processEventStrategyFactory;

    public void process(Event event){
        ProcessEventStrategy strategy =  processEventStrategyFactory.getProcessStrategy(event);
        strategy.process(event);
    }
}
