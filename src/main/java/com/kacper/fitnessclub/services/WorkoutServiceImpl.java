package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Workout;
import com.kacper.fitnessclub.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService{
    @Autowired
    WorkoutRepository workoutRepository;


    @Override
    public List<Workout> getWorkoutList() {
        return workoutRepository.findAll();
    }
}
