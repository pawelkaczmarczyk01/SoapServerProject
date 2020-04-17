package com.example.SoapServerProject.web.service;

import localhost._8080.*;

public interface ReservationService {

    InfoResponse addReservation(ReservationRequest reservationRequest);

    InfoResponse updateReservation(int userId, ReservationRequest reservationRequest);

    InfoResponse deleteReservation(int userId, int reservationId);

    FindReservationByIdResponse findReservationById(int userId, int reservationId);

    FindAllReservationsResponse findAll();

    FindAllReservationsByUserIdResponse findAllReservationByUserId(int userId);
}
