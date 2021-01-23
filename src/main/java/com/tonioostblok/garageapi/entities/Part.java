package com.tonioostblok.garageapi.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "parts")
@DynamicUpdate
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Double price;
    private int quantity;

    public Part(
            String name,
            Double price,
            int quantity
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Part(int id) {
        this.id = id;
    }

    public Part() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice(){
        return this.price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
}
