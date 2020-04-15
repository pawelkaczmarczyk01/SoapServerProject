package com.example.SoapServerProject.web.service;

import localhost._8080.FindAllHotelsResponse;
import localhost._8080.FindHotelByIdResponse;
import localhost._8080.HotelRequest;
import localhost._8080.InfoResponse;

public interface HotelService {

    InfoResponse addHotel(HotelRequest hotelRequest);

    InfoResponse updateHotel(int id, HotelRequest hotelRequest);

    InfoResponse deleteHotel(int id);

    FindHotelByIdResponse findHotelById(int id);

    FindAllHotelsResponse findAll();
}
