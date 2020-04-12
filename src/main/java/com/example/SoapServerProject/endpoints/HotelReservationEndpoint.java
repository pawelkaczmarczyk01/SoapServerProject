package com.example.SoapServerProject.endpoints;

import localhost._8080.GetNameRequest;
import localhost._8080.GetNameResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HotelReservationEndpoint {


    private static final String NAMESPACE_URI = "http://localhost:8080";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
    @ResponsePayload
    public GetNameResponse getNameRequest (@RequestPayload GetNameRequest request) {

        GetNameResponse getNameResponse = new GetNameResponse();
        getNameResponse.setName("Kucu bogiem");

        return getNameResponse;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//    @ResponsePayload
//    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
//
//        return response;
//    }
}
