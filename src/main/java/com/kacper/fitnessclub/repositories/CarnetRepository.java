package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarnetRepository extends JpaRepository<Carnet, Integer> {
}
