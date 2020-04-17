package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.web.service.ReservationService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class ReservationEndpoints {

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @Autowired
    private ReservationService reservationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addReservationRequest")
    @ResponsePayload
    public AddReservationResponse addReservation(@RequestPayload AddReservationRequest addReservationRequest) {
        return reservationService.addReservation(addReservationRequest.getReservation());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteReservationByIdRequest")
    @ResponsePayload
    public DeleteReservationByIdResponse deleteReservation(@RequestPayload DeleteReservationByIdRequest deleteReservationByIdRequest) {
        return reservationService.deleteReservation(deleteReservationByIdRequest.getUserId(), deleteReservationByIdRequest.getReservationId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findReservationByIdRequest")
    @ResponsePayload
    private FindReservationByIdResponse findReservationsById(@RequestPayload FindReservationByIdRequest findReservationByIdRequest) throws DatatypeConfigurationException {
        return reservationService.findReservationById(findReservationByIdRequest.getUserId(), findReservationByIdRequest.getReservationId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllReservationsRequest")
    @ResponsePayload
    public FindAllReservationsResponse findAllReservations() throws DatatypeConfigurationException {
        return reservationService.findAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllReservationsByUserIdRequest")
    @ResponsePayload
    public FindAllReservationsByUserIdResponse findAllReservationsByUserId(@RequestPayload FindAllReservationsByUserIdRequest findAllReservationsByUserIdRequest) throws DatatypeConfigurationException {
        return reservationService.findAllReservationByUserId(findAllReservationsByUserIdRequest.getUserId());
    }
}
