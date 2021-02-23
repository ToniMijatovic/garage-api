package com.tonioostblok.garageapi;

import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.entities.Customer;
import com.tonioostblok.garageapi.repositories.CarRepository;
import com.tonioostblok.garageapi.repositories.CustomerRepository;
import com.tonioostblok.garageapi.services.CarService;
import com.tonioostblok.garageapi.services.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
        @Bean
        public CarService carService() {
            return new CarService();
        }
    }

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CarService carService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CarRepository carRepository;

    private int USER_ID = 1;
    private String USER_FIRSTNAME = "Karel";
    private String USER_LASTNAME = "Doorman";
    private String USER_EMAIL = "karel@gmail.com";
    private String USER_PHONENUMBER = "06123456789";
    private String USER_ADDRESS = "Kareldoormanlaan 13";
    private String USER_ZIPCODE = "1274AA";

    @Before
    public void setUp() {
        Customer customerOnlyWithId = new Customer(
            USER_ID
        );


        Mockito.when(customerRepository.findById(customerOnlyWithId.getId()))
                .thenReturn(java.util.Optional.of(customerOnlyWithId));
    }

    @Test
    public void testGetCustomerMethod() {
        Customer found = customerService.getCustomer(USER_ID);
        assertThat(found.getId())
                .isEqualTo(USER_ID);
    }

    @Test
    public void testAddOrUpdateCustomer() {

        Customer customer = new Customer(
                USER_FIRSTNAME,
                USER_LASTNAME,
                USER_PHONENUMBER,
                USER_EMAIL,
                USER_ADDRESS,
                USER_ZIPCODE
        );

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        Customer created = customerService.addOrUpdateCustomer(customer);

        assertThat(created.getFirstname()).isEqualTo(USER_FIRSTNAME);
        assertThat(created.getLastname()).isEqualTo(USER_LASTNAME);
        assertThat(created.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(created.getPhonenumber()).isEqualTo(USER_PHONENUMBER);
        assertThat(created.getAddress()).isEqualTo(USER_ADDRESS);
        assertThat(created.getZipcode()).isEqualTo(USER_ZIPCODE);
    }
}