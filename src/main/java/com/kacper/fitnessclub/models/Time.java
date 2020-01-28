package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Time {
    @Max(value = 23)
    @Min(value = 01)
    int hour;
    @Max(value = 59)
    @Min(0)
    int minutes;

    Time(int hour, int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }
}
