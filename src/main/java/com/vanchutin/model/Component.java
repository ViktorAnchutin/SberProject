package com.vanchutin.model;


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
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
