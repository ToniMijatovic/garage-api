package com.tonioostblok.garageapi.seeders;

import com.tonioostblok.garageapi.entities.Privilege;
import com.tonioostblok.garageapi.entities.Role;
import com.tonioostblok.garageapi.entities.User;
import com.tonioostblok.garageapi.repositories.PrivilegeRepository;
import com.tonioostblok.garageapi.repositories.RoleRepository;
import com.tonioostblok.garageapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class RolePrivilegeSeeder implements ApplicationListener<ContextRefreshedEvent>
{
        boolean alreadySetup = true;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PrivilegeRepository privilegeRepository;

        @Override
        @Transactional
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (alreadySetup)
                return;
            Privilege readPrivilege
                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege
                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            Collection<Privilege> adminPrivileges = Arrays.asList(
                    readPrivilege, writePrivilege);
            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setFirstname("Henk");
            user.setLastname("Krol");
            user.setPassword("test");
            user.setUsername("test@test.com");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

            alreadySetup = true;
        }

        @Transactional
        Privilege createPrivilegeIfNotFound(String name) {

            Privilege privilege = privilegeRepository.findByName(name);
            if (privilege == null) {
                privilege = new Privilege(name);
                privilegeRepository.save(privilege);
            }
            return privilege;
        }

        @Transactional
        Role createRoleIfNotFound(
                String name, Collection<Privilege> privileges) {

            Role role = roleRepository.findByName(name);
            if (role == null) {
                role = new Role(name);
                role.setPrivileges(privileges);
                roleRepository.save(role);
            }
            return role;
        }
}
