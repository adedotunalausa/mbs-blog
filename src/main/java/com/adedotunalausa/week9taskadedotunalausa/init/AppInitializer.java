package com.adedotunalausa.week9taskadedotunalausa.init;

import com.adedotunalausa.week9taskadedotunalausa.model.ERole;
import com.adedotunalausa.week9taskadedotunalausa.model.Role;
import com.adedotunalausa.week9taskadedotunalausa.repository.RoleRepository;
import com.adedotunalausa.week9taskadedotunalausa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role;
        if(roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
        }

        if(roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
            role = new Role();
            role.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(role);
        }

        if(roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            role = new Role();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }
    }
}
