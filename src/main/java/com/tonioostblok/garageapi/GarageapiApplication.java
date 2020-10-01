package com.tonioostblok.garageapi;

import com.tonioostblok.garageapi.entities.User;
import com.tonioostblok.garageapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GarageapiApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(GarageapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        test();
    }

    private void test() {
//        User karel = new User();
//             karel.setFirstname("Toni");
//             karel.setLastname("Mijatovic");
//             karel.setEmail("tonicroadev@gmail.com");
//             karel.setPassword("password");
//             userService.insertUser(karel);
//        for(User user: userService.getAllUsers()) {
//            System.out.println("id" + user.getId());
//            System.out.println("firstname" + user.getFirstname());
//            System.out.println("lastname" + user.getLastname());
//            System.out.println("email" + user.getEmail());
//            System.out.println("password" + user.getPassword());
//            System.out.println("=========================");
//        }
    }
}
