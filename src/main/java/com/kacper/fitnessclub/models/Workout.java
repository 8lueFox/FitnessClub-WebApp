package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Nazwa nie może być pusta")
    @Size(min = 2, max = 36, message = "Nazwa musi mieć od 2 do 36 znaków")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercises_id"))
    private Set<Exercise> exercises;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @Embedded
    private Time time;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Dzień nie może być pusty")
    private Day day;
    @Positive(message = "Czas trwania musi być dodatni")
    @NotNull(message = "Czas trwania nie może być pusty")
    private int duration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    private Employee employee;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workouts_carnets",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "carnets_id"))
    private Set<Carnet> carnets;

    public Workout(String name, Set<Exercise> exercises, Room room, int hour, int minutes, int duration, Day day, Employee employee, Set<Carnet> carnets){
        this.name = name;
        this.exercises = exercises;
        this.room = room;
        this.time = new Time(hour, minutes);
        this.duration = duration;
        this.day = day;
        this.employee = employee;
        this.carnets = carnets;
    }

    public enum Day{
        Poniedzialek,
        Wtorek,
        Sroda,
        Czwartek,
        Piatek,
        Sobota,
        Niedziela
    }
}
