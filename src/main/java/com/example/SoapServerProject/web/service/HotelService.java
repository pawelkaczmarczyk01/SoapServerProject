package com.example.SoapServerProject.web.service;

import localhost._8080.*;

public interface HotelService {

    AddHotelResponse addHotel(HotelRequest hotelRequest);

    UpdateHotelResponse updateHotel(int id, HotelRequest hotelRequest);

    DeleteHotelByIdResponse deleteHotel(int id);

    FindHotelByIdResponse findHotelById(int id);

    FindAllHotelsResponse findAll();
}
