package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("RepairRepository")
public interface RepairRepository extends CrudRepository<Repair, Integer> {
    public Repair findByName(String name);
}
