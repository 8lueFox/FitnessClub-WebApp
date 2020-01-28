package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carnets")
public class Carnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 36)
    private String name;
    @Positive
    private int price;
    @NotBlank
    @Size(min = 10, max =512)
    private String description;
    @ManyToMany(mappedBy = "carnets")
    private Set<Workout> workouts;

    public Carnet(String name, int price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
