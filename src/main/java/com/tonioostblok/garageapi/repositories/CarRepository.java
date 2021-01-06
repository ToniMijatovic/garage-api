package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CarRepository")
public interface CarRepository extends CrudRepository<Car, Integer> {
    public Car findByBrand(String brand);
}
