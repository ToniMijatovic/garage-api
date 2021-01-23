package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PartRepository")
public interface PartRepository extends CrudRepository<Part, Integer> {
    public Part findByName(String name);
}
