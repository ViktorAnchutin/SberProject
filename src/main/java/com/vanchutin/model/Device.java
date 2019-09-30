package com.vanchutin.model;

import com.vanchutin.model.utils.Status;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "devices")
@Cacheable(false)
public class Device {

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

  /*  @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Component> components; */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setComponents(List<Component> components){
    //    this.components = components;
    }
}
