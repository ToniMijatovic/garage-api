package com.tonioostblok.garageapi;

import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.entities.Part;
import com.tonioostblok.garageapi.repositories.CarRepository;
import com.tonioostblok.garageapi.repositories.PartRepository;
import com.tonioostblok.garageapi.services.CarService;
import com.tonioostblok.garageapi.services.PartService;
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
public class PartServiceTest {

    @TestConfiguration
    static class PartServiceTestContextConfiguration {

        @Bean
        public PartService partService() {
            return new PartService();
        }
    }

    @Autowired
    private PartService partService;

    @MockBean
    private PartRepository partRepository;

    private int PART_ID = 1;

    private String PART_NAME = "Engine";
    private Double PART_PRICE = 10.55;
    private int PART_QUANTITY = 10;


    @Before
    public void setUp() {
        Part part = new Part(
                PART_ID
        );


        Mockito.when(partRepository.findById(part.getId()))
                .thenReturn(java.util.Optional.of(part));
    }

    @Test
    public void testGetCarMethod() {
        Part found = partService.getPart(PART_ID);
        assertThat(found.getId())
                .isEqualTo(PART_ID);
    }

    @Test
    public void testAddOrUpdateCustomer() {

        Part part = new Part(
                PART_NAME,
                PART_PRICE,
                PART_QUANTITY
        );

        Mockito.when(partRepository.save(part)).thenReturn(part);

        Part created = partService.addOrUpdatePart(part);

        assertThat(created.getName()).isEqualTo(PART_NAME);
        assertThat(created.getPrice()).isEqualTo(PART_PRICE);
        assertThat(created.getQuantity()).isEqualTo(PART_QUANTITY);
    }
}