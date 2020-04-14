package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
}
