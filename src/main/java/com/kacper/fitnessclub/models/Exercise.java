package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 2, max = 36)
    private String name;
    @Enumerated(EnumType.STRING)
    @NotNull
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
