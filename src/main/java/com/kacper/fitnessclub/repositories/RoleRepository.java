package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByType(Role.Types type);
}
