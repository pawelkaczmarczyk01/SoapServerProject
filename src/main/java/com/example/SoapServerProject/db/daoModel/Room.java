package com.example.SoapServerProject.db.daoModel;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Room withId(Integer id) {
        setId(id);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    public Integer getHotelId() {
        return hotel.getId();
    }
    public void setHotelId(Integer hotelId) {
        this.hotel.setId(hotelId);
    }
    public Room withHotelId(Integer hotelId) {
        setHotelId(hotelId);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "assortmentId")
    private Assortment assortment;

    public Integer getAssortmentId() {
        return assortment.getId();
    }
    public void setAssortmentId(Integer assortmentId) {
        this.assortment.setId(assortmentId);
    }
    public Room withAssortmentId(Integer assortmentId) {
        setAssortmentId(assortmentId);
        return this;
    }

    @Column
    private String roomName;

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public Room withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    @Column
    private String roomDescription;

    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    public Room withRoomDescription(String roomDescription) {
        setRoomDescription(roomDescription);
        return this;
    }

    @Column
    private Integer roomQuantityOfPeople;

    public Integer getRoomQuantityOfPeople() {
        return roomQuantityOfPeople;
    }
    public void setRoomQuantityOfPeople(Integer roomQuantityOfPeople) {
        this.roomQuantityOfPeople = roomQuantityOfPeople;
    }
    public Room withRoomQuantityOfPeople(Integer roomQuantityOfPeople) {
        setRoomQuantityOfPeople(roomQuantityOfPeople);
        return this;
    }

    @Column
    private Double roomPrice;

    public Double getRoomPrice() {
        return roomPrice;
    }
    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }
    public Room withRoomPrice(Double roomPrice) {
        setRoomPrice(roomPrice);
        return this;
    }

    @Column
    private String roomImagePath;

    public String getRoomImagePath() {
        return roomImagePath;
    }
    public void setRoomImagePath(String roomImagePath) {
        this.roomImagePath = roomImagePath;
    }
    public Room withRoomImagePath(String roomImagePath) {
        setRoomImagePath(roomImagePath);
        return this;
    }
}
