package com.example.SoapServerProject.web.service;

import com.example.SoapServerProject.db.daoModel.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel findHotelById(String id);

    void addHotel(Hotel hotel);

    void deleteHotelById(String id);

    void updateHotel(Hotel hotel);
}
