package com.kacper.fitnessclub.controllers;

import com.kacper.fitnessclub.models.*;
import com.kacper.fitnessclub.repositories.EmployeeRepository;
import com.kacper.fitnessclub.services.AdministerService;
import com.kacper.fitnessclub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdministerController {
    @Autowired
    AdministerService administerService;
    @Autowired
    UserServices userServices;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/administer", method = {RequestMethod.GET, RequestMethod.POST})
    public String administer(Model model){
        model.addAttribute("pracownicy", administerService.getEmployees());
        model.addAttribute("uzytkownicy", administerService.getUsers());
        model.addAttribute("cwiczenia", administerService.getExercises());
        model.addAttribute("pokoje", administerService.getRooms());
        model.addAttribute("treningi", administerService.getWorkouts());
        return "administer";
    }

    @GetMapping(value = "/administer", params = "delEmployee")
    public String delEmployee(Integer delEmployee){
        administerService.delEmployee(delEmployee);
        return "redirect:administer";
    }

    @GetMapping(value = "/administer", params = "hire")
    public String hireEmployee(Integer hire){
        administerService.hireEmployee(new Employee(userServices.getUser(hire), ""));
        return "redirect:administer";
    }

    @GetMapping(value = "/administer", params = "editEmployee")
    public String editEmployee(Model model, Integer editEmployee){
        model.addAttribute("employee", administerService.getEmployee(editEmployee));
        return "employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String editEmployee2(@Valid @ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "administer";
    }

    @GetMapping(value = "/administer", params = "delExercise")
    public String delExercise(Integer delExercise){
        administerService.delExercise(delExercise);
        return "redirect:administer";
    }
    @GetMapping(value = "/administer", params = "delRoom")
    public String delRoom(Integer delRoom){
        administerService.delRoom(delRoom);
        return "redirect:administer";
    }
    @GetMapping(value = "/administer", params = "delWorkout")
    public String delWorkout(Integer delWorkout){
        administerService.delWorkout(delWorkout);
        return "redirect:administer";
    }

    @GetMapping(value = "/administer", params = "editExercise")
    public String editExercise(Model model, Integer editExercise){
        model.addAttribute("exercise", administerService.getExercise(editExercise));
        return "exercise";
    }
    @GetMapping(value = "/administer", params = "editRoom")
    public String editRoom(Model model, Integer editRoom){
        model.addAttribute("room", administerService.getRoom(editRoom));
        return "room";
    }
    @GetMapping(value = "/administer", params = "editWorkout")
    public String editWorkout(Model model, Integer editWorkout){
        model.addAttribute("workout", administerService.getWorkout(editWorkout));
        model.addAttribute("trainers", administerService.getEmployees());
        model.addAttribute("exercises", administerService.getExercises());
        model.addAttribute("rooms", administerService.getRooms());
        model.addAttribute("carnets", administerService.getCarnets());
        return "workoutDetails";
    }
    @GetMapping(value = "/addExercise")
    public String addExercise(Model model){
        model.addAttribute("exercise", new Exercise());
        return "exercise";
    }
    @GetMapping(value = "/addRoom")
    public String addRoom(Model model){
        model.addAttribute("room", new Room());
        return "room";
    }
    @GetMapping(value = "/addWorkout")
    public String addWorkout(Model model){
        model.addAttribute("workout", new Workout());
        model.addAttribute("trainers", administerService.getEmployees());
        model.addAttribute("exercises", administerService.getExercises());
        model.addAttribute("rooms", administerService.getRooms());
        model.addAttribute("carnets", administerService.getCarnets());
        return "workoutDetails";
    }

    @RequestMapping(value = "/administer", method = RequestMethod.POST, params = "exercise")
    public String processFormExercise(@Valid @ModelAttribute("exercise") Exercise exercise){
        administerService.saveExercise(exercise);
        return "redirect:administer";
    }
    @RequestMapping(value = "/administer", method = RequestMethod.POST, params = "room")
    public String processFormRoom(@Valid @ModelAttribute("room") Room room){
        administerService.saveRoom(room);
        return "redirect:administer";
    }
    @RequestMapping(value = "/administer", method = RequestMethod.POST, params = "workout")
    public String processFormWorkout(@Valid @ModelAttribute("workout") Workout workout, BindingResult error){
        if(error.hasErrors())
            return "workoutDetails";
        administerService.saveWorkout(workout);
        return "redirect:administer";
    }

}
