package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 2, max = 36)
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    private boolean enabled;
    @Past
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @NotBlank
    private String email;

    @AssertTrue
    private boolean isPasswordEquls(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    private Integer carnet;

    public User(String username){ this(username, false); }

    public User(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }

    public User(String username, String password, String passwordConfirm, Date birthday, String email){
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.birthday = birthday;
        this.email = email;
    }

    public Integer getId(){
        return this.id;
    }
}