package com.vanchutin.service.processEvent.strategy;

import com.vanchutin.event.Event;

public interface ProcessEventStrategy {
    void process(Event event);
}
