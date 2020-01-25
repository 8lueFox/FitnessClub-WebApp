package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
