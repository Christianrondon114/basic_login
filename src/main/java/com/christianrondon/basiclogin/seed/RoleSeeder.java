package com.christianrondon.basiclogin.seed;

import com.christianrondon.basiclogin.entity.Role;
import com.christianrondon.basiclogin.enums.RoleEnum;
import com.christianrondon.basiclogin.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Profile("dev")
public class RoleSeeder implements ApplicationRunner {
    private final RoleRepository roleRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RoleSeeder.class);

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(RoleEnum.USER));
            roleRepository.save(new Role(RoleEnum.ADMIN));
            logger.info("Roles seeded successfully.");
        } else {
            logger.info("Roles already exist. Skipping seeding.");
        }
    }
}


