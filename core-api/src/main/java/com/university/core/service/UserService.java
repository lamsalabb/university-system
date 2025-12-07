package com.university.core.service;

import com.university.common.entity.User;
import com.university.common.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User registrationUser){

        String hashedPassword = passwordEncoder.encode(registrationUser.getPasswordHash());

        User user = User.builder()
                .email(registrationUser.getEmail())
                .passwordHash(hashedPassword)
                .role(registrationUser.getRole())
                .firstName(registrationUser.getFirstName())
                .lastName(registrationUser.getLastName())
                .isActive(true)
                .build();

        return userRepository.save(user);
    }

    public Boolean userExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
