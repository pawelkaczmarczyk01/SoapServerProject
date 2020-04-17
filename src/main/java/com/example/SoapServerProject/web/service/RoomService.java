package com.example.SoapServerProject.web.service;

import localhost._8080.*;

public interface RoomService {

    AddRoomResponse addRoom(RoomRequest roomRequest);

    UpdateRoomResponse updateRoom(int id, RoomRequest roomRequest);

    DeleteRoomByIdResponse deleteRoom(int id);

    FindRoomByIdResponse findRoomById(int id);

    FindAllRoomsResponse findAll();

    FindAllRoomsByHotelIdResponse findAllRoomsByHotelId(int id);
}
