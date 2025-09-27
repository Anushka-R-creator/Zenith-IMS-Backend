package com.zenith.ims.service;

import com.zenith.ims.model.Role;
import com.zenith.ims.model.User;
import com.zenith.ims.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        if (userRepository.count() == 0) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@zenith.com");
            adminUser.setPassword(passwordEncoder.encode("adminpassword"));
            adminUser.setRole(Role.ROLE_ADMIN);
            adminUser.setCompanyId("DEFAULT_COMPANY");
            userRepository.save(adminUser);
            System.out.println(">>> Default admin user created successfully!");
        }

        alreadySetup = true;
    }
}

