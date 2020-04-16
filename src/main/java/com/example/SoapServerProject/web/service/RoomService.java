package com.example.SoapServerProject.web.service;

import localhost._8080.*;

public interface RoomService {

    InfoResponse addRoom(RoomRequest roomRequest);

    InfoResponse updateRoom(int id, RoomRequest roomRequest);

    InfoResponse deleteRoom(int id);

    FindRoomByIdResponse findRoomById(int id);

    FindAllRoomsResponse findAll();

    FindAllRoomsByHotelIdResponse findAllRoomsByHotelId(int id);
}
