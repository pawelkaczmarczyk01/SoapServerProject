package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.ReservationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Reservation extends JpaRepository<ReservationDao, Integer> {
}
