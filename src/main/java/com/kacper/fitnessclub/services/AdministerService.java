package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.*;

import java.util.List;

public interface AdministerService {
    List<Employee> getEmployees();
    List<User> getUsers();
    List<Exercise> getExercises();
    List<Room> getRooms();
    List<Workout> getWorkouts();
    List<Carnet> getCarnets();
    User getUser(Integer id);
    Employee getEmployee(Integer id);
    Exercise getExercise(Integer id);
    Room getRoom(Integer id);
    Workout getWorkout(Integer id);

    void hireEmployee(Employee employee);
    void delEmployee(Integer id);

    void delExercise(Integer id);
    void delRoom(Integer id);
    void delWorkout(Integer id);
    void saveExercise(Exercise exercise);
    void saveRoom(Room room);
    void saveWorkout(Workout workout);

}
