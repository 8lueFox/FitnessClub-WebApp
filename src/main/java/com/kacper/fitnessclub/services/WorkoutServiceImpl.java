package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Workout;
import com.kacper.fitnessclub.models.commands.WorkoutFilter;
import com.kacper.fitnessclub.repositories.WorkoutRepository;
import com.kacper.fitnessclub.repositories.specifications.WorkoutSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService{
    @Autowired
    WorkoutRepository workoutRepository;

    @Override
    public Page<Workout> getWorkoutList(Pageable pageable) {
        Page page = workoutRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Workout> getAllWorkouts(WorkoutFilter search, Pageable pageable) {
        Page page = workoutRepository.findAll(
                Specification.where(
                        WorkoutSpecification.findByName(search.getName()).and(
//                                WorkoutSpecification.findByCarnet(search.getCarnet()).and(
//                                        WorkoutSpecification.findByDay(search.getDay()).and(
                                                WorkoutSpecification.findByTrainer(search.getTrainer()).and(
                                                        WorkoutSpecification.findByHour(search.getHour())
                                                )
                                        )
//                                )
//                        )
                ),pageable);
        return page;
    }
}
