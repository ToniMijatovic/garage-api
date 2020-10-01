package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Role;
import com.tonioostblok.garageapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("RoleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByName(String name);
}
