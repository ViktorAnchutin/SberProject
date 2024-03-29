package com.vanchutin.service.processEvent.strategy;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.event.Event;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessErrorStrategy implements ProcessEventStrategy {

    @Autowired @Setter
    ComponentDao componentDao;

    @Override
    public void process(Event event) {
        componentDao.setStatusFalse(event.getDeviceId(), event.getComponentId());
    }
}
