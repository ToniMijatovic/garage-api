package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> addCustomer(HttpServletRequest request){
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(HttpServletRequest request){
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/customer", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(HttpServletRequest request){
        return ResponseEntity.ok("ok");
    }
}
