package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
