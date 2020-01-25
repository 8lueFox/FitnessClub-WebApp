package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;
    @ManyToMany(mappedBy = "exercises")
    private Set<Workout> workouts;

    public Exercise(String name, Level level){
        this.name = name;
        this.level = level;
    }

    public enum Level{
        Easy,
        Medium,
        Hard
    }
}
