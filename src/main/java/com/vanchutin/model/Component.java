package com.vanchutin.model;

import lombok.Getter;
import lombok.Setter;


public class Component {

    public Component(String name, Device device){
        this.name = name;
        this.device = device;
        this.status = true;
    }

    public Component(){}

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Setter
    private boolean status;

    @Getter @Setter
    private Device device;

    public boolean getStatus(){
        return this.status;
    }


}
