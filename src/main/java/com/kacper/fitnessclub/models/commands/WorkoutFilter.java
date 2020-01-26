package com.kacper.fitnessclub.models.commands;

import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.util.StringUtils;

@Getter
@Setter
public class WorkoutFilter {

    private String name;
    private String trainer;
    private String carnet;
    private String day;
    private Float hour;

    public boolean isEmpty(){
        return StringUtils.isEmpty(name) && StringUtils.isEmpty(trainer) && StringUtils.isEmpty(carnet) && StringUtils.isEmpty(day) && hour == null;
    }

    public void clear(){
        this.name = null;
        this.trainer = null;
        this.carnet = null;
        this.day = null;
        this.hour = null;
    }

    public String getNameLike(){
        if(StringUtils.isEmpty(name)) return null;
        return "%"+name+"%";
    }
    public String getTrainerLike(){
        if(StringUtils.isEmpty(trainer)) return null;
        return "%"+trainer+"%";
    }
    public String getCarnetLike(){
        if(StringUtils.isEmpty(carnet)) return null;
        return "%"+carnet+"%";
    }
    public String getDayLike(){
        if(StringUtils.isEmpty(day)) return null;
        return "%"+day+"%";
    }
}
