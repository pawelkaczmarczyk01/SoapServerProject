package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.ReservationDAO;
import com.example.SoapServerProject.db.dao.RoomDAO;
import com.example.SoapServerProject.db.daoModel.Reservation;
import com.example.SoapServerProject.db.daoModel.Room;
import com.example.SoapServerProject.db.daoModel.User;
import com.example.SoapServerProject.web.service.ReservationService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private RoomDAO roomDAO;

    @Override
    public InfoResponse addReservation(ReservationRequest reservationRequest) {
        Room room = roomDAO.findRoomById(reservationRequest.getRoomId());

        if (room != null) {
            Reservation reservation = new Reservation()
                    .withUserId(new User().withId(reservationRequest.getUserId()))
                    .withRoomId(new Room().withId(reservationRequest.getRoomId()))
                    .withReservationFrom(reservationRequest.getRoomReservationFrom().toGregorianCalendar().getTime())
                    .withReservationTo(reservationRequest.getRoomReservationTo().toGregorianCalendar().getTime());
            if (reservation.getReservationFrom().getTime() > reservation.getRoomReservationTo().getTime()) {
                return createInfoResponseMessage("Date from can't be lover than date to");
            }
            if (reservation.getReservationFrom().getTime() == reservation.getRoomReservationTo().getTime()) {
                return createInfoResponseMessage("Date from can't be equals date to");
            }

            List<Reservation> reservationList = reservationDAO.findAll();
            reservationList = reservationList.stream().filter(roomId -> roomId.getRoomId().getId().equals(reservationRequest.getRoomId())).collect(Collectors.toList());

            for (Reservation reservationFromDb : reservationList) {
                if (reservation.getReservationFrom().getTime() <= reservationFromDb.getReservationFrom().getTime() && reservation.getRoomReservationTo().getTime() <= reservationFromDb.getReservationFrom().getTime()) {
                    System.out.println("git_1");
                } else if (reservation.getReservationFrom().getTime() >= reservationFromDb.getRoomReservationTo().getTime() && reservation.getRoomReservationTo().getTime() >= reservationFromDb.getRoomReservationTo().getTime()) {
                    System.out.println("git_1");
                } else {
                    return createInfoResponseMessage("This Room is reserved in this time");
                }
            }
            reservationDAO.save(reservation);
            return createInfoResponseMessage("Successfully added reservation");
        } else {
            throw new ServiceException("This Room is not existed");
        }
    }

    @Override
    public InfoResponse updateReservation(int id, ReservationRequest reservationRequest) {
        return null;
    }

    @Override
    public InfoResponse deleteReservation(int userId, int reservationId) {
        return null;
    }

    @Override
    public FindReservationByIdResponse findReservationById(int userId, int reservationId) {
        return null;
    }

    @Override
    public FindAllReservationsResponse findAll() {
        return null;
    }

    @Override
    public FindAllReservationsByUserIdResponse findAllReservationByUserId(int userId) {
        return null;
    }

    public InfoResponse createInfoResponseMessage(String message) {
        InfoResponse infoResponse = new InfoResponse();
        infoResponse.setInfo(message);
        return infoResponse;
    }
}
