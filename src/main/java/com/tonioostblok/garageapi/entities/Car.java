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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_files",
            joinColumns = @JoinColumn(
                    name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "file_id", referencedColumnName = "id"))
    private Collection<File> files;

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

    public Car(int id){
        this.id = id;
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
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicense_plate() {
        return this.license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getMot() {
        return this.mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public void setFiles(Collection<File> files) {
        this.files = files;
    }
    public Collection<File> getFiles() {
        return this.files;
    }
}
