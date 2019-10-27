package com.vanchutin.listener;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Message {
    private int deviceId;
    private int componentId;
    private String eventType;
}
