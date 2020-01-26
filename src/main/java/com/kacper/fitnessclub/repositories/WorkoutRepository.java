package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>, JpaSpecificationExecutor<Workout> {
}
