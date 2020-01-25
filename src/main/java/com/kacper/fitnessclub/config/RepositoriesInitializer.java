package com.kacper.fitnessclub.config;

import com.kacper.fitnessclub.models.Carnet;
import com.kacper.fitnessclub.models.Role;
import com.kacper.fitnessclub.models.User;
import com.kacper.fitnessclub.repositories.CarnetRepository;
import com.kacper.fitnessclub.repositories.RoleRepository;
import com.kacper.fitnessclub.repositories.UserRepository;
import com.kacper.fitnessclub.services.UserServices;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

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

                userRepository.save(admin);
                userRepository.save(menager);
                userRepository.save(employee);
                userRepository.save(user);
            }
            if(carnetRepository.findAll().isEmpty()){
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

            }
        };
    }
}
