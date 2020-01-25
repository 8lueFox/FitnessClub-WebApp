package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
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
    @NotBlank
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
    private Day day;
    @Positive
    private int duration;

    public Workout(String name, Set<Exercise> exercises, Room room, int hour, int minutes, int duration, Day day){
        this.name = name;
        this.exercises = exercises;
        this.room = room;
        this.time = new Time(hour, minutes);
        this.duration = duration;
        this.day = day;
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
