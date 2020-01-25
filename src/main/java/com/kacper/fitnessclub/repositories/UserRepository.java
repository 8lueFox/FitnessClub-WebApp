package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
