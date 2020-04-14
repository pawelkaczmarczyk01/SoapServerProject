package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDAO extends JpaRepository<Hotel, Integer> {

    Hotel findHotelById(int id);

    void deleteHotelById(int id);
}
