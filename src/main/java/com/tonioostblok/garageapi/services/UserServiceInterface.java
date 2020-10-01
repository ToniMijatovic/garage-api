package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class UserServiceInterface implements UserDetailsService {
    public Iterable<User> getAllUsers() {
        return null;
    }

    public User getUser(int id) {
        return null;
    }

    public User insertUser(User user) {
        return null;
    }

    public void deleteUser(int id) {}

    public User loadUserByUsername(String email){
        return null;
    }

}
