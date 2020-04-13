package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.RoomDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Room extends JpaRepository<RoomDao, Integer> {
}
