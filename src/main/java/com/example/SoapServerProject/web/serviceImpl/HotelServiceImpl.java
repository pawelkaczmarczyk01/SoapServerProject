package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.AssortmentDAO;
import com.example.SoapServerProject.db.dao.HotelDAO;
import com.example.SoapServerProject.db.dao.RoomDAO;
import com.example.SoapServerProject.db.daoModel.Hotel;
import com.example.SoapServerProject.db.daoModel.Room;
import com.example.SoapServerProject.web.service.HotelService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.MTOM;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@MTOM
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private AssortmentDAO assortmentDAO;

    @Override
    public AddHotelResponse addHotel(HotelRequest hotelRequest) {
        Hotel hotelFromDb = hotelDAO.findHotelByHotelName(hotelRequest.getHotelName());
        if (hotelFromDb == null) {
            Hotel hotel = new Hotel()
                    .withHotelName(hotelRequest.getHotelName())
                    .withHotelImagePath(hotelRequest.getHotelImagePath());
            hotelDAO.save(hotel);

            return createAddHotelResponseMessage("Successfully added hotel");
        } else {
            throw new ServiceException("Hotel name must be unique");
        }
    }

    @Override
    public UpdateHotelResponse updateHotel(int id, HotelRequest hotelRequest) {
        Hotel hotelFromDb = Optional.ofNullable(hotelDAO.findHotelByHotelName(hotelRequest.getHotelName())).orElse(new Hotel());

        if (hotelFromDb.getId() == null) {
            Hotel hotel = hotelDAO.findHotelById(id);
            if (hotel != null) {
                if (!hotelRequest.getHotelName().isEmpty() && hotelRequest.getHotelName() != null) {
                    hotel.setHotelName(hotelRequest.getHotelName());
                }
                if (!hotelRequest.getHotelImagePath().isEmpty() && hotelRequest.getHotelImagePath() != null) {
                    hotel.setHotelImagePath(hotelRequest.getHotelImagePath());
                }
                hotelDAO.save(hotel);

                return createUpdateHotelResponseMessage("Successfully updated hotel");
            } else {
                throw new ServiceException("Hotel with given id is not existed");
            }
        } else {
            throw new ServiceException("Hotel name must be unique");
        }
    }

    @Override
    public DeleteHotelByIdResponse deleteHotel(int id) {
        if (hotelDAO.findHotelById(id) != null) {
            List<Room> roomListFromDb = roomDAO.findAll();
            roomListFromDb = roomListFromDb.stream().filter(hotelId -> hotelId.getHotelId().getId().equals(id)).collect(Collectors.toList());

            for (Room roomFromDb : roomListFromDb) {
                assortmentDAO.deleteAssortmentById(roomFromDb.getAssortmentId().getId());
                roomDAO.deleteRoomById(roomFromDb.getId());
            }

            hotelDAO.deleteHotelById(id);

            return createDeleteHotelByIdResponseMessage("Successfully deleted hotel");
        } else {
            throw new ServiceException("Hotel with given id is not existed");
        }
    }

    @Override
    public FindHotelByIdResponse findHotelById(int id) {
        Hotel hotel = hotelDAO.findHotelById(id);
        FindHotelByIdResponse findHotelByIdResponse = new FindHotelByIdResponse();
        HotelResponse hotelResponse = new HotelResponse();

        hotelResponse.setId(hotel.getId());
        hotelResponse.setHotelName(hotel.getHotelName());
        hotelResponse.setHotelImagePath(hotel.getHotelImagePath());

        findHotelByIdResponse.setHotel(hotelResponse);
        return findHotelByIdResponse;
    }

    @Override
    public FindAllHotelsResponse findAll() {
        List<Hotel> hotelListFromDb = hotelDAO.findAll();
        FindAllHotelsResponse findAllHotelsResponse = new FindAllHotelsResponse();

        for (Hotel hotelFromDb : hotelListFromDb) {
            HotelResponse hotelResponse = new HotelResponse();
            hotelResponse.setId(hotelFromDb.getId());
            hotelResponse.setHotelName(hotelFromDb.getHotelName());
            hotelResponse.setHotelImagePath(hotelFromDb.getHotelImagePath());
            findAllHotelsResponse.getHotelList().add(hotelResponse);
        }

        return findAllHotelsResponse;
    }

    public AddHotelResponse createAddHotelResponseMessage(String message) {
        AddHotelResponse addHotelResponse = new AddHotelResponse();
        addHotelResponse.setInfo(message);
        return addHotelResponse;
    }

    public UpdateHotelResponse createUpdateHotelResponseMessage(String message) {
        UpdateHotelResponse updateHotelResponse = new UpdateHotelResponse();
        updateHotelResponse.setInfo(message);
        return updateHotelResponse;
    }

    public DeleteHotelByIdResponse createDeleteHotelByIdResponseMessage(String message) {
        DeleteHotelByIdResponse deleteHotelByIdResponse = new DeleteHotelByIdResponse();
        deleteHotelByIdResponse.setInfo(message);
        return deleteHotelByIdResponse;
    }
}
