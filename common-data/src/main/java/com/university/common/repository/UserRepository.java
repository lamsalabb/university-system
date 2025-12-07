package com.university.common.repository;

import com.university.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email); //Using optional because the search might return null

    List<User> findByRole(User.Role role);

}
