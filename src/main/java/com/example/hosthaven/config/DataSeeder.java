package com.example.hosthaven.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.hosthaven.entity.SystemUser;
import com.example.hosthaven.repository.SystemUserRepository;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(SystemUserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.count() == 0) {

                SystemUser admin = new SystemUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@hosthaven.com");
                admin.setFullName("System Admin");
                admin.setRole("HOSPITALITY_ADMIN");
                userRepository.save(admin);

                SystemUser owner = new SystemUser();
                owner.setUsername("owner");
                owner.setPassword(passwordEncoder.encode("owner123"));
                owner.setEmail("owner@hosthaven.com");
                owner.setFullName("Property Owner");
                owner.setRole("PROPERTY_OWNER");
                userRepository.save(owner);

                SystemUser guest = new SystemUser();
                guest.setUsername("guest");
                guest.setPassword(passwordEncoder.encode("guest123"));
                guest.setEmail("guest@hosthaven.com");
                guest.setFullName("Regular Guest");
                guest.setRole("GUEST");
                userRepository.save(guest);

                SystemUser mani = new SystemUser();
                mani.setUsername("mani");
                mani.setPassword(passwordEncoder.encode("mani123"));
                mani.setEmail("mani@hosthaven.com");
                mani.setFullName("Mani Kumar");
                mani.setRole("GUEST");
                userRepository.save(mani);

                System.out.println("Default users seeded successfully.");
            }

        };
    }
}