package com.kacper.fitnessclub.config;

import com.kacper.fitnessclub.models.*;
import com.kacper.fitnessclub.repositories.*;
import com.kacper.fitnessclub.services.UserServices;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RepositoriesInitializer {

    // TODO: zainicjalizować wszystkie dane
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CarnetRepository carnetRepository;
    @Autowired
    private UserServices userServices;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Bean
    InitializingBean init(){
        return () -> {
            if(roleRepository.findAll().isEmpty()){
                Role roleUser = roleRepository.save(new Role(Role.Types.USER));
                Role roleAdmin = roleRepository.save(new Role(Role.Types.ADMIN));
                Role roleMenager = roleRepository.save(new Role(Role.Types.MENAGER));
                Role roleEmployee = roleRepository.save(new Role(Role.Types.EMPLOYEE));

                User user = new User("user", true);
                user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                user.setPassword(passwordEncoder.encode("user"));
                user.setEmail("user@int.pl");

                User admin = new User("admin", true);
                admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEmail("admin@int.pl");

                User menager = new User("menager", true);
                menager.setRoles(new HashSet<>(Arrays.asList(roleMenager)));
                menager.setPassword(passwordEncoder.encode("menager"));
                menager.setEmail("menager@int.pl");

                User employee = new User("employee", true);
                employee.setRoles(new HashSet<>(Arrays.asList(roleEmployee)));
                employee.setPassword(passwordEncoder.encode("employee"));
                employee.setEmail("employee@int.pl");

                User employee2 = new User("employee2", true);
                employee.setRoles(new HashSet<>(Arrays.asList(roleEmployee)));
                employee.setPassword(passwordEncoder.encode("employee"));
                employee.setEmail("employee@int.pl");

                userRepository.save(admin);
                userRepository.save(menager);
                userRepository.save(employee);
                userRepository.save(user);

                Carnet student = new Carnet("Karnet STUDENT", 69, "<ul>\n" +
                        "<li>Wejście za free przez cały miesiąc</li>\n" +
                        "<li>Dostęp do siłowni</li>\n" +
                        "<li>Konsultacje z trenerem i pomiary składu ciała</li>\n" +
                        "<li>Płatność automatyczna kartą</li>\n" +
                        "<li>Zniżka podczas sesji</li>\n" +
                        "</ul>");
                Carnet normal = new Carnet("Karnet NORMAL", 99, "<ul>\n" +
                        "<li>Wejście za free przez cały miesiąc</li>\n" +
                        "<li>Dostęp do siłowni i wybranych zajęć</li>\n" +
                        "<li>Konsultacje z trenerem i pomiary składu ciała</li>\n" +
                        "<li>Płatność automatyczna kartą</li>\n" +
                        "</ul>");
                Carnet senior = new Carnet("Karnet SENIOR", 95, "<ul>\n" +
                        "<li>Wejście za free przez cały miesiąc</li>\n" +
                        "<li>Dostęp do siłowni i wybranych zajęć</li>\n" +
                        "<li>Konsultacje z trenerem i pomiary składu ciała</li>\n" +
                        "<li>Płatność automatyczna kartą</li>\n" +
                        "</ul>");
                carnetRepository.save(student);
                carnetRepository.save(normal);
                carnetRepository.save(senior);

                Exercise exercise1 = new Exercise("Plank", Exercise.Level.Hard);
                Exercise exercise2 = new Exercise("Skakanka", Exercise.Level.Medium);
                Exercise exercise3 = new Exercise("Brzuszki", Exercise.Level.Easy);
                exerciseRepository.save(exercise1);
                exerciseRepository.save(exercise2);
                exerciseRepository.save(exercise3);

                Room room1 = new Room("Mała sala nr 1");
                Room room2 = new Room("Mała sala nr 2");
                Room room3 = new Room("Duża sala nr 1");
                roomRepository.save(room1);
                roomRepository.save(room2);
                roomRepository.save(room3);

                Set<Exercise> z1 = new HashSet<>();
                z1.add(exercise1);
                z1.add(exercise2);

                Set<Exercise> z2 = new HashSet<>();
                z2.add(exercise1);
                z2.add(exercise3);

                Set<Exercise> z3 = new HashSet<>();
                z3.add(exercise2);
                z3.add(exercise3);

                Set<Carnet> c1 = new HashSet<>();
                Set<Carnet> c2 = new HashSet<>();
                Set<Carnet> c3 = new HashSet<>();

                c1.add(senior);
                c2.add(student);
                c2.add(normal);

                c3.add(normal);

                Employee e1 = new Employee(employee, "Kasia");

                employeeRepository.save(e1);

                Workout workout1 = new Workout("Tabata", z1, room1, 15, 0, 45, Workout.Day.Poniedzialek, e1, c3);
                Workout workout2 = new Workout("Szybkie spalanie", z3, room2, 9, 30, 60, Workout.Day.Sroda, e1, c2);
                Workout workout3 = new Workout("Be Younger", z2, room3, 19, 45, 30, Workout.Day.Piatek, e1, c1);

                workoutRepository.save(workout1);
                workoutRepository.save(workout2);
                workoutRepository.save(workout3);
            }
        };
    }
}
