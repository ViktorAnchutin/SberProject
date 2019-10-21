package com.vanchutin.receiver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component
@Slf4j
public class MessageMapper {

    @Autowired
    ObjectMapper objectMapper;


    public Optional<Message> map(String message){

        int deviceId;
        int componentId;
        String eventType;

        try{
            JsonNode jsonNode = objectMapper.readTree(message);
            eventType = jsonNode.get("type").textValue();
            deviceId = jsonNode.get("deviceId").intValue();
            componentId = jsonNode.get("componentId").intValue();
        }catch (IOException e) {
            log.error(String.format("Error when parsing JSON message: %s", e.getMessage()));
            return Optional.empty();
        }
        return Optional.of(new Message(deviceId, componentId, eventType));
    }
}
