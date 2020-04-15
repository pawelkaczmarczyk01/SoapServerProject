package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.db.daoModel.Hotel;
import com.example.SoapServerProject.web.service.HotelService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class HotelEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @Autowired
    private HotelService hotelService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addHotelRequest")
    @ResponsePayload
    public void addHotel(@RequestPayload AddHotelRequest request) {
        Hotel hotel = new Hotel()
                .withHotelName(request.getHotel().getHotelName())
                .withHotelDescription(request.getHotel().getHotelDescription())
                .withHotelImagePath(request.getHotel().getHotelImagePath());
        hotelService.addHotel(hotel);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateHotelRequest")
    @ResponsePayload
    public void updateHotel(@RequestPayload UpdateHotelRequest request) {
        Hotel hotelFromDb = hotelService.findHotelById(request.getId());
        if (!request.getHotel().getHotelName().isEmpty() && request.getHotel().getHotelName() != null) {
            hotelFromDb.setHotelName(request.getHotel().getHotelName());
        }
        if (!request.getHotel().getHotelDescription().isEmpty() && request.getHotel().getHotelDescription() != null) {
            hotelFromDb.setHotelDescription(request.getHotel().getHotelDescription());
        }
        if (!request.getHotel().getHotelImagePath().isEmpty() && request.getHotel().getHotelImagePath() != null) {
            hotelFromDb.setHotelImagePath(request.getHotel().getHotelImagePath());
        }

        hotelService.updateHotel(hotelFromDb);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteHotelByIdRequest")
    @ResponsePayload
    public void deleteHotel(@RequestPayload DeleteHotelByIdRequest request) {
        hotelService.deleteHotelById(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findHotelByIdRequest")
    @ResponsePayload
    public FindHotelByIdResponse findHotelById(@RequestPayload FindHotelByIdRequest request) {
        Hotel hotelFromDb = hotelService.findHotelById(request.getId());
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setId(hotelFromDb.getId());
        hotelResponse.setHotelName(hotelFromDb.getHotelName());
        hotelResponse.setHotelDescription(hotelFromDb.getHotelDescription());
        hotelResponse.setHotelImagePath(hotelFromDb.getHotelImagePath());

        FindHotelByIdResponse response = new FindHotelByIdResponse();
        response.setHotel(hotelResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllHotelRequest")
    @ResponsePayload
    public FindAllHotelResponse findAllHotels() {
        List<Hotel> hotelsListFromDb = hotelService.findAll();
        FindAllHotelResponse response = new FindAllHotelResponse();

        for (Hotel hotelFromDb: hotelsListFromDb) {
            HotelResponse hotelResponse = new HotelResponse();
            hotelResponse.setId(hotelFromDb.getId());
            hotelResponse.setHotelName(hotelFromDb.getHotelName());
            hotelResponse.setHotelDescription(hotelFromDb.getHotelDescription());
            hotelResponse.setHotelImagePath(hotelFromDb.getHotelImagePath());
            response.getHotelList().add(hotelResponse);
        }

        return response;
    }
}
