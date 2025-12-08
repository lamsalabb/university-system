package com.university.core.service;

import com.university.common.entity.User;
import com.university.common.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUser(User registrationUser){

        if (userRepository.findByEmail(registrationUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException();
        }
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

    public User findUserById(int id){
        return userRepository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> findAllByRole(User.Role role){
        return userRepository.findByRole(role);
    }
}
