package com.tonioostblok.garageapi.entities;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String type;
    private String license_plate;
    private String mot;

    public Car(
            String brand,
            String type,
            String license_plate,
            String mot
    ){
        this.brand = brand;
        this.type = type;
        this.license_plate = license_plate;
        this.mot = mot;
    }

    public Car(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }
}
