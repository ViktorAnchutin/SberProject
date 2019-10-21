package com.vanchutin.receiver;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Message {
    private int deviceId;
    private int componentId;
    private String eventType;
}
