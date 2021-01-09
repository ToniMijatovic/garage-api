package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.*;
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

    @Autowired
    private CarService carService;

    public Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id) {
        return customerRepository.findById(id).get();
    }

    public Customer addOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteUser(int id) {
        customerRepository.deleteById(id);
    }

    public Customer addCarToCustomer(int customer_id, int car_id) {
        Customer customer = this.getCustomer(customer_id);

        Car car = carService.getCar(car_id);

        Collection<Car> currentCars = customer.getCars();

        currentCars.add(car);

        customer.setCars(currentCars);

        this.addOrUpdateCustomer(customer);

        return customer;
    }

    public Customer removeCarFromCustomer(int customer_id, int car_id) {
        Customer customer = this.getCustomer(customer_id);

        Collection<Car> customerCars = customer.getCars();
        customerCars.removeIf(p -> p.getId() == car_id);

        customer.setCars(customerCars);

        this.addOrUpdateCustomer(customer);
        return customer;
    }
}
