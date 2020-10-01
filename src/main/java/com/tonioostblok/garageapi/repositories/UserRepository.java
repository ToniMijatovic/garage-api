package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
