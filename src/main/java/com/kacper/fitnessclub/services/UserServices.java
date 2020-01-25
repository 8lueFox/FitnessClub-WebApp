package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {
    void save(User user);

    void confirmUser(String username);

    boolean isUniqueLogin(String username);

    void setCarnetForUser(Integer carnetId, String usernmae);

    User getUser(Integer id);

    Integer getUser(String username);

    Integer checkCarnet(Integer userId);
}
