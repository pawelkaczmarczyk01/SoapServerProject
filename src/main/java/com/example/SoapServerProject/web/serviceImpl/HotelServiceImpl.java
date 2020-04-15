package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.HotelDAO;
import com.example.SoapServerProject.db.daoModel.Hotel;
import com.example.SoapServerProject.web.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Hotel> findAll() {
        return hotelDAO.findAll();
    }

    @Override
    public Hotel findHotelById(String id) {
        return hotelDAO.findHotelById(Integer.valueOf(id));
    }

    @Override
    public void addHotel(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Override
    public void deleteHotelById(String id) {
        hotelDAO.deleteHotelById(Integer.valueOf(id));
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Hotel hotelFromDAO = hotelDAO.findHotelById(hotel.getId());
        hotelFromDAO.withHotelName(hotel.getHotelName())
                .withHotelDescription(hotel.getHotelDescription())
                .withHotelImagePath(hotel.getHotelImagePath());
        this.entityManager.persist(hotelFromDAO);
    }
}
