package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Carnet;
import com.kacper.fitnessclub.repositories.CarnetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarnetServiceImpl implements CarnetService{
    @Autowired
    CarnetRepository carnetRepository;

    @Override
    public List<Carnet> getCartens() {
        return carnetRepository.findAll();
    }

    @Override
    public Carnet getCartner(Integer id) {
        return carnetRepository.findAll().get(id);
    }
}
