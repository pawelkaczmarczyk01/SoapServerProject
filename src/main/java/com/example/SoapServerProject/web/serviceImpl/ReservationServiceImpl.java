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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
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
    public AddReservationResponse addReservation(ReservationRequest reservationRequest) {
        Room room = roomDAO.findRoomById(reservationRequest.getRoomId());

        if (room != null) {
            Reservation reservation = new Reservation()
                    .withUserId(new User().withId(reservationRequest.getUserId()))
                    .withRoomId(new Room().withId(reservationRequest.getRoomId()))
                    .withReservationFrom(reservationRequest.getRoomReservationFrom().toGregorianCalendar().getTime())
                    .withReservationTo(reservationRequest.getRoomReservationTo().toGregorianCalendar().getTime());
            if (reservation.getReservationFrom().getTime() > reservation.getRoomReservationTo().getTime()) {
                return createAddReservationResponseMessage("Date from can't be lover than date to");
            }
            if (reservation.getReservationFrom().getTime() == reservation.getRoomReservationTo().getTime()) {
                return createAddReservationResponseMessage("Date from can't be equals date to");
            }

            List<Reservation> reservationList = reservationDAO.findAll();
            reservationList = reservationList.stream().filter(roomId -> roomId.getRoomId().getId().equals(reservationRequest.getRoomId())).collect(Collectors.toList());

            for (Reservation reservationFromDb : reservationList) {
                if (reservation.getReservationFrom().getTime() <= reservationFromDb.getReservationFrom().getTime() && reservation.getRoomReservationTo().getTime() <= reservationFromDb.getReservationFrom().getTime()) {
                    System.out.println("git_1");
                } else if (reservation.getReservationFrom().getTime() >= reservationFromDb.getRoomReservationTo().getTime() && reservation.getRoomReservationTo().getTime() >= reservationFromDb.getRoomReservationTo().getTime()) {
                    System.out.println("git_1");
                } else {
                    return createAddReservationResponseMessage("This Room is reserved in this time");
                }
            }
            reservationDAO.save(reservation);
            return createAddReservationResponseMessage("Successfully added reservation");
        } else {
            throw new ServiceException("This Room is not existed");
        }
    }

    @Override
    public DeleteReservationByIdResponse deleteReservation(int userId, int reservationId) {
        Reservation reservation = reservationDAO.findReservationById(reservationId);
        if (reservation != null) {
            if (reservation.getUserId().getId() == userId) {
                reservationDAO.deleteReservationById(reservationId);
                return createDeleteReservationByIdResponseMessage("Successfully deleted hotel");
            } else {
                throw new ServiceException("Access denied !!");
            }
        } else {
            throw new ServiceException("Reservation with given id is not existed");
        }
    }

    @Override
    public FindReservationByIdResponse findReservationById(int userId, int reservationId) throws DatatypeConfigurationException {
        Reservation reservation = reservationDAO.findReservationById(reservationId);
        if (reservation.getUserId().getId().equals(userId)) {
            FindReservationByIdResponse findReservationByIdResponse = new FindReservationByIdResponse();
            ReservationResponse reservationResponse = getReservationResponse(reservation);
            findReservationByIdResponse.setReservation(reservationResponse);
            return findReservationByIdResponse;
        } else {
            throw new ServiceException("Access denied !!");
        }
    }

    @Override
    public FindAllReservationsResponse findAll() throws DatatypeConfigurationException {
        List<Reservation> reservationListFromDb = reservationDAO.findAll();
        FindAllReservationsResponse findAllReservationsResponse = new FindAllReservationsResponse();

        for (Reservation reservationFromDb : reservationListFromDb) {
            findAllReservationsResponse.getReservationList().add(getReservationResponse(reservationFromDb));
        }
        return findAllReservationsResponse;
    }

    @Override
    public FindAllReservationsByUserIdResponse findAllReservationByUserId(int userId) throws DatatypeConfigurationException {
        List<Reservation> reservationListFromDb = reservationDAO.findAll();
        reservationListFromDb = reservationListFromDb.stream().filter(reservation -> reservation.getUserId().getId().equals(userId)).collect(Collectors.toList());

        FindAllReservationsByUserIdResponse findAllReservationsByUserIdResponse = new FindAllReservationsByUserIdResponse();

        for (Reservation reservationFromDb : reservationListFromDb) {
            findAllReservationsByUserIdResponse.getReservationList().add(getReservationResponse(reservationFromDb));
        }
        return findAllReservationsByUserIdResponse;
    }

    private ReservationResponse getReservationResponse(Reservation reservationFromDb) throws DatatypeConfigurationException {
        ReservationResponse reservationResponse = new ReservationResponse();

        GregorianCalendar gregorianCalendarFrom = new GregorianCalendar();
        GregorianCalendar gregorianCalendarTo = new GregorianCalendar();
        gregorianCalendarFrom.setTime(reservationFromDb.getReservationFrom());
        gregorianCalendarTo.setTime(reservationFromDb.getRoomReservationTo());

        reservationResponse.setRoomId(new Room().withId(reservationFromDb.getRoomId().getId()).getId());
        reservationResponse.setUserId(new User().withId(reservationFromDb.getUserId().getId()).getId());
        reservationResponse.setRoomReservationFrom(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendarFrom));
        reservationResponse.setRoomReservationTo(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendarTo));
        return reservationResponse;
    }

    public AddReservationResponse createAddReservationResponseMessage(String message) {
        AddReservationResponse addReservationResponse = new AddReservationResponse();
        addReservationResponse.setInfo(message);
        return addReservationResponse;
    }

    public DeleteReservationByIdResponse createDeleteReservationByIdResponseMessage(String message) {
        DeleteReservationByIdResponse deleteReservationByIdResponse = new DeleteReservationByIdResponse();
        deleteReservationByIdResponse.setInfo(message);
        return deleteReservationByIdResponse;
    }
}
