package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.web.service.HotelService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HotelEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @Autowired
    private HotelService hotelService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addHotelRequest")
    @ResponsePayload
    public AddHotelResponse addHotel(@RequestPayload AddHotelRequest addHotelRequest) {
        return hotelService.addHotel(addHotelRequest.getHotel());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateHotelRequest")
    @ResponsePayload
    public UpdateHotelResponse updateHotel(@RequestPayload UpdateHotelRequest updateHotelRequest) {
        return hotelService.updateHotel(updateHotelRequest.getId(), updateHotelRequest.getHotel());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteHotelByIdRequest")
    @ResponsePayload
    public DeleteHotelByIdResponse deleteHotel(@RequestPayload DeleteHotelByIdRequest deleteHotelByIdRequest) {
        return hotelService.deleteHotel(deleteHotelByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findHotelByIdRequest")
    @ResponsePayload
    public FindHotelByIdResponse findHotelById(@RequestPayload FindHotelByIdRequest findHotelByIdRequest) {
        return hotelService.findHotelById(findHotelByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllHotelsRequest")
    @ResponsePayload
    public FindAllHotelsResponse findAllHotels() {
        return hotelService.findAll();
    }
}
