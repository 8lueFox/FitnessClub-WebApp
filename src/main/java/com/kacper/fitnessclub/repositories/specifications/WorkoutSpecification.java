package com.kacper.fitnessclub.repositories.specifications;

import com.kacper.fitnessclub.models.*;
import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.util.StringUtils;

import java.nio.file.Path;

public class WorkoutSpecification {
    public static Specification<Workout> findByName(final String name){
        return (root, query, cb) -> {
            if(StringUtils.isEmpty(name)) return null;
            String phraseLike = "%"+name.toUpperCase()+"%";
            return cb.like(cb.upper(root.get(Workout_.name)), phraseLike);
        };
    }

    public static Specification<Workout> findByTrainer(final String trainer){
        return (root, query, cb) -> {
            if(StringUtils.isEmpty(trainer)) return null;
            String phraseLike = "%"+trainer.toUpperCase()+"%";
            return cb.like(cb.upper(root.get(Workout_.user).get(User_.USERNAME)), phraseLike);
        };
    }

//    public static Specification<Workout> findByCarnet(final String carnet){
//        return (root, query, cb) -> {
//            if(StringUtils.isEmpty(carnet)) return null;
//            String phraseLike = "%"+carnet.toUpperCase()+"%";
//            return cb.like(cb.upper(root.get("carnet")), phraseLike);
//        };
//    }

    public static Specification<Workout> findByHour(Float hour){
        return (root, query, cb) -> {
            if(hour == null) return null;
            return cb.between(root.get(Workout_.time).get(Time_.HOUR), hour, 24f);
        };
    }

//    public static Specification<Workout> findByDay(final String day){
//        return (root, query, cb) -> {
//            if(StringUtils.isEmpty(day)) return null;
//            String phraseLike = "%"+day.toUpperCase()+"%";
//            return cb.like(cb.upper(root.get(Workout_.day), phraseLike);
//        };
//    }
}
