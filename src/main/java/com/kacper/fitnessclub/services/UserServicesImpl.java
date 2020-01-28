package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.config.ProfileNames;
import com.kacper.fitnessclub.exceptions.ObjectNotFoundException;
import com.kacper.fitnessclub.models.Employee;
import com.kacper.fitnessclub.models.Role;
import com.kacper.fitnessclub.models.User;
import com.kacper.fitnessclub.repositories.CarnetPurchaseHistoryRepository;
import com.kacper.fitnessclub.repositories.EmployeeRepository;
import com.kacper.fitnessclub.repositories.RoleRepository;
import com.kacper.fitnessclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Profile(ProfileNames.DATABASE)
@Service
public class UserServicesImpl implements UserServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CarnetPurchaseHistoryRepository carnetPurchaseHistoryRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void save(User user) {
        Role userRole = roleRepository.findRoleByType(Role.Types.USER);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void confirmUser(String username) {
        User user = userRepository.findUserByUsername(username);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findUserByUsername(username) == null;
    }

    @Override
    public void setCarnetForUser(Integer carnetId, String username) {
        User user = userRepository.findUserByUsername(username);
        user.setCarnet(carnetId);
        userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElse(new User());
        return user;
    }

    @Override
    public Integer getUser(String username){
        return userRepository.findUserByUsername(username).getId();
    }

    @Override
    public Integer checkCarnet(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElse(new User());
        if(carnetPurchaseHistoryRepository.findCarnetBoughtByUser(user.getId()) != null){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return createUSerDetails(user);
    }

    private UserDetails createUSerDetails(User user){
        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getType().toString())
                ).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }

//    private List<User> sprawdzTO(){
//        List<Employee> listEm = employeeRepository.findAll();
//        return userRepository.;
//    }
}
