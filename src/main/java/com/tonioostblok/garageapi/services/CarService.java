package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.*;
import com.tonioostblok.garageapi.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("carService")
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(int id) {
        return carRepository.findById(id).get();
    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public Car addOrUpdateCar(Car car) {
        return carRepository.save(car);
    }

    public void addFileToCar(File file, int car_id){
        Car car = this.getCar(car_id);

        Collection<File> files = car.getFiles();

        files.add(file);

        this.addOrUpdateCar(car);
    }

    public void removeFileFromCar(int car_id, File file){
        Car car = this.getCar(car_id);

        Collection<File> files = car.getFiles();

        files.remove(file);

        car.setFiles(files);

        this.addOrUpdateCar(car);
    }
}
