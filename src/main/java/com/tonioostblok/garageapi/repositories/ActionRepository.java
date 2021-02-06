package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ActionRepository")
public interface ActionRepository extends CrudRepository<Action, Integer> {
    public Action findByTitle(String title);
}
