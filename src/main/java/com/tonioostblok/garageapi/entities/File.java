package com.tonioostblok.garageapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File extends BaseEntity {

    private String name;
    private String url;

    public File(
            String name,
            String url
    ) {
        this.name = name;
        this.url = url;
    }

    public File(int id) {
        this.setId(id);
    }

    public File() {

    }

    public String getUrl() {
        return this.url;
    }

    public void setUr(String url) {
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
