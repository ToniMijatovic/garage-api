package com.tonioostblok.garageapi;

import com.tonioostblok.garageapi.entities.File;
import com.tonioostblok.garageapi.repositories.FileRepository;
import com.tonioostblok.garageapi.services.FileService;
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
public class FileServiceTest {

    @TestConfiguration
    static class FileServiceTestContextConfiguration {

        @Bean
        public FileService fileService() {
            return new FileService();
        }
    }

    @Autowired
    private FileService fileService;

    @MockBean
    private FileRepository fileRepository;

    private int FILE_ID = 1;

    private String FILE_NAME = "Kentekenbewijs";
    private String FILE_URL = "/image.png";


    @Before
    public void setUp() {
        File file = new File(
                FILE_ID
        );


        Mockito.when(fileRepository.findById(file.getId()))
                .thenReturn(java.util.Optional.of(file));
    }

    @Test
    public void testGetFile() {
        File found = fileService.getFile(FILE_ID);
        assertThat(found.getId())
                .isEqualTo(FILE_ID);
    }

    @Test
    public void testAddOrUpdateCustomer() {

        File file = new File(
                FILE_NAME,
                FILE_URL
        );

        Mockito.when(fileRepository.save(file)).thenReturn(file);

        File created = fileService.addOrUpdateFile(file);

        assertThat(created.getName()).isEqualTo(FILE_NAME);
        assertThat(created.getUrl()).isEqualTo(FILE_URL);
    }
}