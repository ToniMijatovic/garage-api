package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> car(@PathVariable(value = "id") int car_id, HttpServletRequest request) {
        try {
            Car car = carService.getCar(car_id);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"The car that you have tried to fetch does not exist.\"}");
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<?> addCar(@RequestBody Car car, HttpServletRequest request) {
        try {
            Car savedCar = carService.addOrUpdateCar(car);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to add a car.\"}");
        }
    }
    @RequestMapping(value = "/car", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCar(@RequestBody Car car, HttpServletRequest request) {
        try {
            Car savedCar = carService.addOrUpdateCar(car);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to update a new car.\"}");
        }
    }
    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable(value = "id") int car_id, HttpServletRequest request) {
        try {
            carService.deleteCar(car_id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to update a new car.\"}");
        }
    }
}
