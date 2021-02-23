package com.tonioostblok.garageapi;

import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.repositories.CarRepository;
import com.tonioostblok.garageapi.services.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CarServiceTest {

    @TestConfiguration
    static class CarServiceTestContextConfiguration {

        @Bean
        public CarService carService() {
            return new CarService();
        }
    }

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    private int CAR_ID = 1;

    private String CAR_BRAND = "Opel";
    private String CAR_TYPE = "Corsa";
    private String CAR_LICENSE_PLATE = "NLAP32GB";
    private String CAR_MOT = "2020-01-01";


    @Before
    public void setUp() {
        Car car = new Car(
                CAR_ID
        );


        Mockito.when(carRepository.findById(car.getId()))
                .thenReturn(java.util.Optional.of(car));
    }

    @Test
    public void testGetCarMethod() {
        Car found = carService.getCar(CAR_ID);
        assertThat(found.getId())
                .isEqualTo(CAR_ID);
    }

    @Test
    public void testAddOrUpdateCustomer() {

        Car car = new Car(
                CAR_BRAND,
                CAR_TYPE,
                CAR_LICENSE_PLATE,
                CAR_MOT
        );

        Mockito.when(carRepository.save(car)).thenReturn(car);

        Car created = carService.addOrUpdateCar(car);

        assertThat(created.getBrand()).isEqualTo(CAR_BRAND);
        assertThat(created.getType()).isEqualTo(CAR_TYPE);
        assertThat(created.getLicense_plate()).isEqualTo(CAR_LICENSE_PLATE);
        assertThat(created.getMot()).isEqualTo(CAR_MOT);
    }
}