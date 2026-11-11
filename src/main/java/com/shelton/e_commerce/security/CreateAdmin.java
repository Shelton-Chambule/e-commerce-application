package com.shelton.e_commerce.security;

import com.shelton.e_commerce.entity.User;
import com.shelton.e_commerce.entity.enums.AccountRole;
import com.shelton.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class CreateAdmin implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmail("admin@gmail.com")) {
            throw new IllegalArgumentException("this email already exists");
        }

        User admin = new User();
        admin.setName("Administrador Shelton");
        admin.setRole(AccountRole.ADMIN);
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("123Chambule"));
        admin.setPhone("873307789");
        userRepository.save(admin);
    }
}
