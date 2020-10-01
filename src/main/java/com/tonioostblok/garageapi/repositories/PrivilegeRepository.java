package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Privilege;
import com.tonioostblok.garageapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PrivilegeRepository")
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    public Privilege findByName(String name);
}
