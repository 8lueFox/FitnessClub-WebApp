package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.*;
import com.kacper.fitnessclub.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministerServiceImpl implements AdministerService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    CarnetRepository carnetRepository;
    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Carnet> getCarnets() { return carnetRepository.findAll(); }

    @Override
    public User getUser(Integer id) { return userRepository.findById(id).orElse(new User()); }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).orElse(new Employee());
    }

    @Override
    public Exercise getExercise(Integer id) {
        return exerciseRepository.findById(id).orElse(new Exercise());
    }

    @Override
    public Room getRoom(Integer id) {
        return roomRepository.findById(id).orElse(new Room());
    }

    @Override
    public Workout getWorkout(Integer id) {
        return workoutRepository.findById(id).orElse(new Workout());
    }

    @Override
    public void delEmployee(Integer id) { employeeRepository.delete(employeeRepository.findById(id).orElse(new Employee())); }

    @Override
    public void delExercise(Integer id) { exerciseRepository.delete(exerciseRepository.findById(id).orElse(new Exercise())); }

    @Override
    public void delRoom(Integer id) {
        roomRepository.delete(roomRepository.findById(id).orElse(new Room()));
    }

    @Override
    public void delWorkout(Integer id) { workoutRepository.delete(workoutRepository.findById(id).orElse(new Workout())); }

    @Override
    public void saveExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    @Override
    public void hireEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
