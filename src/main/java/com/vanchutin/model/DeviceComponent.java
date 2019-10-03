package com.vanchutin.model;

import com.vanchutin.model.Component;
import com.vanchutin.model.Device;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public @Data class DeviceComponent implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Device device;

    @Id
    @ManyToOne
    @JoinColumn
    private Component component;


    private boolean component_status;
}
