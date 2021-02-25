package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.Role;
import com.tonioostblok.garageapi.entities.User;
import com.tonioostblok.garageapi.repositories.RoleRepository;
import com.tonioostblok.garageapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserService extends UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        User user = userRepository.findById(id).get();
        user.setAuthorities(this.getAuthorities(user.getRoles()));
        return user;
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String email) {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return new User(
                        " ", " ", true, true, true, true,
                        getAuthorities(Arrays.asList(
                                roleRepository.findByName("ROLE_USER"))));
            }
            user.setAuthorities(getAuthorities(user.getRoles()));
            return user;
        }

    private Collection<GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getRoles(roles));
    }

    private List<String> getRoles(Collection<Role> roles) {
        List<String> rolesList = new ArrayList<>();
        for (Role role : roles) {
            rolesList.add(role.getName());
        }
        return rolesList;
    }
    private Collection<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
