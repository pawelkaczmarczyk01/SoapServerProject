package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.web.service.RoomService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RoomEndpoints {

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @Autowired
    private RoomService roomService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRoomRequest")
    @ResponsePayload
    public InfoResponse addRoom(@RequestPayload AddRoomRequest addRoomRequest) {
        return roomService.addRoom(addRoomRequest.getRoom());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRoomRequest")
    @ResponsePayload
    public InfoResponse updateRoom(@RequestPayload UpdateRoomRequest updateRoomRequest) {
        return roomService.updateRoom(updateRoomRequest.getId(), updateRoomRequest.getRoom());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteRoomByIdRequest")
    @ResponsePayload
    public InfoResponse deleteRoom(@RequestPayload DeleteRoomByIdRequest deleteRoomByIdRequest) {
        return roomService.deleteRoom(deleteRoomByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findRoomByIdRequest")
    @ResponsePayload
    public FindRoomByIdResponse findRoomById(@RequestPayload FindRoomByIdRequest findRoomByIdRequest) {
        return roomService.findRoomById(findRoomByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllRoomsRequest")
    @ResponsePayload
    public FindAllRoomsResponse findAllRooms() {
        return roomService.findAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllRoomsByHotelIdRequest")
    @ResponsePayload
    public FindAllRoomsByHotelIdResponse findAllRoomsByHotelId(@RequestPayload FindAllRoomsByHotelIdRequest findAllRoomsByHotelIdRequest) {
        return roomService.findAllRoomsByHotelId(findAllRoomsByHotelIdRequest.getHotelId());
    }
}
