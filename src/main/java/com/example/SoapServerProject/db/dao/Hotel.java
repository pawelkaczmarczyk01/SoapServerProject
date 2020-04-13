package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.HotelDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Hotel extends JpaRepository<HotelDao, Integer> {
}
