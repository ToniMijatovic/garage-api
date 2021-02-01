package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.entities.ErrorMessage;
import com.tonioostblok.garageapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> customer(@PathVariable(value = "id") int customer_id) {
        try {
            Customer customer = customerService.getCustomer(customer_id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The customer that you have tried to fetch does not exist."));
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addOrUpdateCustomer(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to add a customer."));
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        try {
            customerService.addOrUpdateCustomer(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to add a customer."));
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") int customer_id) {
        try {
            customerService.deleteUser(customer_id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to add a customer."));
        }
    }

    @RequestMapping(value = "/customer/{customer_id}/car/{car_id}", method = RequestMethod.POST)
    public ResponseEntity<?> addCarToCustomer(@PathVariable(value = "customer_id") int customer_id, @PathVariable(value = "car_id") int car_id) {
        try {
            Customer customer = customerService.addCarToCustomer(customer_id, car_id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("{\"error:" + e.getCause() + "}");
        }
    }

    @RequestMapping(value = "/customer/{customer_id}/car/{car_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCarFromCustomer(@PathVariable(value = "customer_id") int customer_id, @PathVariable(value = "car_id") int car_id) {
        try {
            Customer customer = customerService.removeCarFromCustomer(customer_id, car_id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("{\"error:" + e.getCause() + "}");
        }
    }
}
