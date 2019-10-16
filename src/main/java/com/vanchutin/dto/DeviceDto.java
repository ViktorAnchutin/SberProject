package com.vanchutin.dto;

import com.vanchutin.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class DeviceDto {

    public DeviceDto(String name, Status status){
        this.name = name;
        this.status = status;
    }

    public DeviceDto(String name){
        this.name = name;
    }

    public DeviceDto(long id, Status status){
        this.id = id;
        this.status = status;
    }

    public DeviceDto(long id){
        this.id = id;
    }

    private long id;
    private String name;
    private Status status;
}
