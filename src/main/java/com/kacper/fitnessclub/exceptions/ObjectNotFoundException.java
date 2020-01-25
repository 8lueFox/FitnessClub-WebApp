package com.kacper.fitnessclub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such object")
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(){
        super(String.format("Obiekt nie istnieje"));
    }

    public ObjectNotFoundException(Integer id){
        super(String.format("Obiekt o id %d nie istnieje", id));
    }
}
