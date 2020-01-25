package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Workout;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkoutService {
    List<Workout> getWorkoutList();
}
