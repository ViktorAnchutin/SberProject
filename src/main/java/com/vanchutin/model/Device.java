package com.vanchutin.model;

import com.vanchutin.model.utils.Status;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public @Data class Device {

    public Device(int id, String name, Status status){
        this.id = id;
        this.name = name;
        this.status = status;
    }

    private Integer id;

    private String name;

    private Status status;
}
