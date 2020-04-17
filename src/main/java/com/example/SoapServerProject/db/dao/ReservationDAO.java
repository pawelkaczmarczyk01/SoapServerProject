package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
    Reservation findReservationById(int id);

    void deleteReservationById(int id);
}
