package com.kacper.fitnessclub.controllers;

import com.kacper.fitnessclub.models.commands.WorkoutFilter;
import com.kacper.fitnessclub.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@Controller
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;

    @RequestMapping(value = "/workout", method = {RequestMethod.GET, RequestMethod.POST})
    public String getWorkouts(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand")WorkoutFilter search){
        model.addAttribute("workoutListPage", workoutService.getAllWorkouts(search, pageable));
        return "workout";
    }

    @PostMapping(value="/workout", params = {"all"})
    public String resetVehicleList(@ModelAttribute("searchCommand") WorkoutFilter search){
        search.clear();
        return "redirect:workout";
    }
}
