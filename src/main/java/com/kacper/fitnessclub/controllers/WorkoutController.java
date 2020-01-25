package com.kacper.fitnessclub.controllers;

import com.kacper.fitnessclub.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;

    @GetMapping(value = "/workout")
    public String getWorkouts(Model model){
        model.addAttribute("workoutList", workoutService.getWorkoutList());
        return "workout";
    }
}
