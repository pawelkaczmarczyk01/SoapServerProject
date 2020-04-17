package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.AssortmentDAO;
import com.example.SoapServerProject.db.dao.HotelDAO;
import com.example.SoapServerProject.db.dao.RoomDAO;
import com.example.SoapServerProject.db.daoModel.Assortment;
import com.example.SoapServerProject.db.daoModel.Hotel;
import com.example.SoapServerProject.db.daoModel.Room;
import com.example.SoapServerProject.web.service.RoomService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private AssortmentDAO assortmentDAO;

    @Override
    public AddRoomResponse addRoom(RoomRequest roomRequest) {
        if (hotelDAO.findHotelById(roomRequest.getHotelId()) == null) {
            throw new ServiceException("Hotel with given id doesn't exist");
        }
        try {
            Assortment assortment = new Assortment()
                    .withRoomBathroom(roomRequest.isRoomBathroom())
                    .withRoomDesk(roomRequest.isRoomDesk())
                    .withRoomFridge(roomRequest.isRoomFridge())
                    .withRoomSafe(roomRequest.isRoomSafe())
                    .withRoomTv(roomRequest.isRoomTv());
            assortment = assortmentDAO.save(assortment);

            Room room = new Room()
                    .withHotelId(new Hotel().withId(roomRequest.getHotelId()))
                    .withAssortmentId(new Assortment().withId(assortment.getId()))
                    .withRoomDescription(roomRequest.getRoomDescription())
                    .withRoomName(roomRequest.getRoomName())
                    .withRoomPrice(roomRequest.getRoomPrice())
                    .withRoomQuantityOfPeople(roomRequest.getRoomQuantityOfPeople())
                    .withRoomImagePath(roomRequest.getRoomImagePath());

            roomDAO.save(room);
            return createAddRoomResponseMessage("Successfully added room with assortment");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public UpdateRoomResponse updateRoom(int id, RoomRequest roomRequest) {
        if (hotelDAO.findHotelById(roomRequest.getHotelId()) == null) {
            throw new ServiceException("Hotel with given id doesn't exist");
        }
        try {
            Room roomFromDb = roomDAO.findRoomById(id);
            if (roomFromDb != null) {
                if (!roomRequest.getRoomName().isEmpty() && roomRequest.getRoomName() != null) {
                    roomFromDb.setRoomName(roomRequest.getRoomName());
                }
                if (!roomRequest.getRoomDescription().isEmpty() && roomRequest.getRoomDescription() != null) {
                    roomFromDb.setRoomDescription(roomRequest.getRoomDescription());
                }
                if (!roomRequest.getRoomImagePath().isEmpty() && roomRequest.getRoomImagePath() != null) {
                    roomFromDb.setRoomImagePath(roomRequest.getRoomImagePath());
                }
                if (roomRequest.getRoomQuantityOfPeople() != null && roomRequest.getRoomQuantityOfPeople() != 0) {
                    roomFromDb.setRoomQuantityOfPeople(roomRequest.getRoomQuantityOfPeople());
                }
                if (roomRequest.getRoomPrice() != null) {
                    roomFromDb.setRoomPrice(roomRequest.getRoomPrice());
                }
                Assortment assortmentFromDb = assortmentDAO.findAssortmentById(roomFromDb.getAssortmentId().getId());
                if (roomRequest.isRoomBathroom() != null) {
                    assortmentFromDb.setRoomBathroom(roomRequest.isRoomBathroom());
                }
                if (roomRequest.isRoomDesk() != null) {
                    assortmentFromDb.setRoomDesk(roomRequest.isRoomDesk());
                }
                if (roomRequest.isRoomFridge() != null) {
                    assortmentFromDb.setRoomFridge(roomRequest.isRoomFridge());
                }
                if (roomRequest.isRoomSafe() != null) {
                    assortmentFromDb.setRoomSafe(roomRequest.isRoomSafe());
                }
                if (roomRequest.isRoomTv() != null) {
                    assortmentFromDb.setRoomTv(roomRequest.isRoomTv());
                }

                assortmentDAO.save(assortmentFromDb);
                roomDAO.save(roomFromDb);

                return createUpdateRoomResponseMessage("Successfully updated room with assortment");
            } else {
                throw new ServiceException("Room with given id is not existed");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public DeleteRoomByIdResponse deleteRoom(int id) {
        Room roomFromDb = roomDAO.findRoomById(id);
        if (roomFromDb != null) {
            assortmentDAO.deleteAssortmentById(roomFromDb.getAssortmentId().getId());
            roomDAO.deleteRoomById(id);
            return createDeleteRoomByIdResponseMessage("Successfully deleted room");
        } else {
            throw new ServiceException("Room with given id is not existed");
        }
    }

    @Override
    public FindRoomByIdResponse findRoomById(int id) {
        Room room = roomDAO.findRoomById(id);
        Assortment assortment = assortmentDAO.findAssortmentById(room.getAssortmentId().getId());
        FindRoomByIdResponse findRoomByIdResponse = new FindRoomByIdResponse();
        RoomResponse roomResponse = getRoomResponse(room);
        roomResponse = getAssortmentResponse(roomResponse, assortment);
        findRoomByIdResponse.setRoom(roomResponse);
        return findRoomByIdResponse;
    }

    @Override
    public FindAllRoomsResponse findAll() {
        List<Room> roomListFromDb = roomDAO.findAll();
        FindAllRoomsResponse findAllRoomsResponse = new FindAllRoomsResponse();

        for (Room roomFromDb : roomListFromDb) {
            Assortment assortment = assortmentDAO.findAssortmentById(roomFromDb.getAssortmentId().getId());
            RoomResponse roomResponse = getRoomResponse(roomFromDb);
            roomResponse = getAssortmentResponse(roomResponse, assortment);
            findAllRoomsResponse.getRoomList().add(roomResponse);
        }

        return findAllRoomsResponse;
    }

    @Override
    public FindAllRoomsByHotelIdResponse findAllRoomsByHotelId(int id) {
        List<Room> roomListFromDb = roomDAO.findAll();
        roomListFromDb = roomListFromDb.stream().filter(hotelId -> hotelId.getHotelId().getId().equals(id)).collect(Collectors.toList());

        FindAllRoomsByHotelIdResponse findAllRoomsByHotelIdResponse = new FindAllRoomsByHotelIdResponse();

        for (Room roomFromDb : roomListFromDb) {
            Assortment assortment = assortmentDAO.findAssortmentById(roomFromDb.getAssortmentId().getId());
            RoomResponse roomResponse = getRoomResponse(roomFromDb);
            roomResponse = getAssortmentResponse(roomResponse, assortment);
            findAllRoomsByHotelIdResponse.getRoomList().add(roomResponse);
        }

        return findAllRoomsByHotelIdResponse;
    }

    public RoomResponse getAssortmentResponse(RoomResponse roomResponse, Assortment assortment) {
        roomResponse.setRoomBathroom(assortment.getRoomBathroom());
        roomResponse.setRoomDesk(assortment.getRoomDesk());
        roomResponse.setRoomFridge(assortment.getRoomFridge());
        roomResponse.setRoomSafe(assortment.getRoomSafe());
        roomResponse.setRoomTv(assortment.getRoomTv());
        return roomResponse;
    }

    public RoomResponse getRoomResponse(Room room) {
        RoomResponse roomResponse = new RoomResponse();
        if (room.getAssortmentId() != null) {
            roomResponse.setAssortmentId(room.getAssortmentId().getId());
        }
        roomResponse.setId(room.getId());
        roomResponse.setHotelId(new Hotel().withId(room.getHotelId().getId()).getId());
        roomResponse.setRoomDescription(room.getRoomDescription());
        roomResponse.setRoomImagePath(room.getRoomImagePath());
        roomResponse.setRoomName(room.getRoomName());
        roomResponse.setRoomPrice(room.getRoomPrice());
        roomResponse.setRoomQuantityOfPeople(room.getRoomQuantityOfPeople());
        roomResponse.setAssortmentId(room.getAssortmentId().getId());
        return roomResponse;
    }

    public AddRoomResponse createAddRoomResponseMessage(String message) {
        AddRoomResponse addRoomResponse = new AddRoomResponse();
        addRoomResponse.setInfo(message);
        return addRoomResponse;
    }

    public UpdateRoomResponse createUpdateRoomResponseMessage(String message) {
        UpdateRoomResponse updateRoomResponse = new UpdateRoomResponse();
        updateRoomResponse.setInfo(message);
        return updateRoomResponse;
    }

    public DeleteRoomByIdResponse createDeleteRoomByIdResponseMessage(String message) {
        DeleteRoomByIdResponse deleteRoomByIdResponse = new DeleteRoomByIdResponse();
        deleteRoomByIdResponse.setInfo(message);
        return deleteRoomByIdResponse;
    }
}
