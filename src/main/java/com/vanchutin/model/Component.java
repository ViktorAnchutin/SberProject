package com.vanchutin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "components")
public class Component {
    public Component(String name, Device device){
        this.name = name;
        this.device = device;
        this.status = true;
    }

    public Component(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    @Getter @Setter
    private String name;

    @Column(name = "status")
    @Setter
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @Getter @Setter
    private Device device;

    public boolean getStatus(){return this.status;}


}
