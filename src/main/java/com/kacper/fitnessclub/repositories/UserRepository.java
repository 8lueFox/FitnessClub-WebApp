package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Employee;
import com.kacper.fitnessclub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

//    List<User> findAllByIdIsNot(List<Long>);// TODO: check this shit
}
