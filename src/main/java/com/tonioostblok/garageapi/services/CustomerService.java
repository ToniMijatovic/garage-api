package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.entities.Privilege;
import com.tonioostblok.garageapi.entities.Role;
import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("customerService")
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id) {
        return customerRepository.findById(id).get();
    }

}
