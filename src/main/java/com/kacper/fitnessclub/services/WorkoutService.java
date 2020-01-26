package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Workout;
import com.kacper.fitnessclub.models.commands.WorkoutFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkoutService {
    Page<Workout> getWorkoutList(Pageable pageable);
    Page<Workout> getAllWorkouts(WorkoutFilter search, Pageable pageable);
}
