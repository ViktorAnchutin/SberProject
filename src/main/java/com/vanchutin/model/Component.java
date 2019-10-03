package com.vanchutin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "components")
public class Component {

    public Component(String name){
        this.name = name;
    }

    public Component(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "component")
    private Set<DeviceComponent> deviceComponents;
}
