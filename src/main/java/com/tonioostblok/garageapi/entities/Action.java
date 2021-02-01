package com.tonioostblok.garageapi.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class Action extends BaseEntity {

    private String title;
    private String content;
    private Double price;
    public Action(
            String title,
            String content,
            Double price
    ) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Action(int id) {
        this.setId(id);
    }

    public Action() {

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
