package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Types type;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){ this.type = Types.USER; }

    public Role(Types type) { this.type = type; }

    public enum Types{
        ADMIN,
        USER,
        MENAGER,
        EMPLOYEE
    }
}
