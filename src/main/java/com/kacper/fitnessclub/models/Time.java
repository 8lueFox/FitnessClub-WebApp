package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Time {
    int hour, minutes;

    Time(int hour, int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }
}
