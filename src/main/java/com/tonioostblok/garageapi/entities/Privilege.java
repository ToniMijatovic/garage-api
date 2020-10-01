package com.tonioostblok.garageapi.entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "privileges", cascade = CascadeType.ALL)
    private Collection<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }

    public Privilege() {

    }

    public String getName(){
        return this.name;
    }
}