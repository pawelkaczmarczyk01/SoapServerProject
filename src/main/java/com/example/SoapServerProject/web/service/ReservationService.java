package com.example.SoapServerProject.web.service;

import localhost._8080.*;

import javax.xml.datatype.DatatypeConfigurationException;

public interface ReservationService {

    AddReservationResponse addReservation(ReservationRequest reservationRequest);

    DeleteReservationByIdResponse deleteReservation(int userId, int reservationId);

    FindReservationByIdResponse findReservationById(int userId, int reservationId) throws DatatypeConfigurationException;

    FindAllReservationsResponse findAll() throws DatatypeConfigurationException;

    FindAllReservationsByUserIdResponse findAllReservationByUserId(int userId) throws DatatypeConfigurationException;
}
