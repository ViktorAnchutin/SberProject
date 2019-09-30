package com.vanchutin.model;

import com.vanchutin.model.utils.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "devices")
@Cacheable(false)
public @Data class Device {

    public Device(){}

    public Device(String name){
        this.name = name;
        this.status = Status.normal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;
}
