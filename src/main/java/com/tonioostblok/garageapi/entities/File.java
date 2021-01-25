package com.tonioostblok.garageapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File extends BaseEntity {

    private String file;

    public File(
            String file
    ) {
        this.file = file;
    }

    public File(int id) {
        this.setId(id);
    }

    public File() {

    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
