package com.tonioostblok.garageapi.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "repair")
public class Repair extends BaseEntity{

    private String name;
    private String description;
    private Timestamp scheduled_at;
    private String status;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "repair_parts",
            joinColumns = @JoinColumn(
                    name = "repair_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "part_id", referencedColumnName = "id"))

    private Collection<Part> parts;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "repair_action",
            joinColumns = @JoinColumn(
                    name = "repair_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "action_id", referencedColumnName = "id"))

    private Collection<Action> actions;

    public Repair(String name, String description, Timestamp scheduled_at, String status) {
        this.name = name;
        this.description = description;
        this.scheduled_at = scheduled_at;
        this.status = status;
    }

    public Repair(int id) {
        this.setId(id);
    }

    public Repair() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getScheduled_at() {
        return scheduled_at;
    }

    public void setScheduled_at(Timestamp scheduled_at) {
        this.scheduled_at = scheduled_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Collection<Part> getParts() {
        return parts;
    }

    public void setParts(Collection<Part> parts) {
        this.parts = parts;
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = actions;
    }

}
