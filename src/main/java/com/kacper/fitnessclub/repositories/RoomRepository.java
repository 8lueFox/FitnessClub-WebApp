package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
