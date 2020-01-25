package com.kacper.fitnessclub.services;

import com.kacper.fitnessclub.models.Carnet;
import java.util.List;

public interface CarnetService {
    List<Carnet> getCartens();

    Carnet getCartner(Integer id);
}
