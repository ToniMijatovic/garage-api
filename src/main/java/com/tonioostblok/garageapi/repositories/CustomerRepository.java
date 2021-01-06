package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CustomerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public Customer findByEmail(String email);
}
