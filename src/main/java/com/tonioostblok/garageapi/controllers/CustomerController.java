package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> customer(@PathVariable(value = "id") int customer_id, HttpServletRequest request){
        try{
            Customer customer = customerService.getCustomer(customer_id);
            return ResponseEntity.ok(customer);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("{\"error\":\"The customer that you have tried to fetch does not exist.\"}");
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody  Customer customer, HttpServletRequest request){
        try{
            customerService.addOrUpdateCustomer(customer);
            return ResponseEntity.ok(customer);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to add a customer.\"}");
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody  Customer customer, HttpServletRequest request){
        try{
            customerService.addOrUpdateCustomer(customer);
            return ResponseEntity.ok(customer);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to add a customer.\"}");
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") int customer_id, HttpServletRequest request){
        try{
            customerService.deleteUser(customer_id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to add a customer.\"}");
        }
    }
}
