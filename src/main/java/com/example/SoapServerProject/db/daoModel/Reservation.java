package com.example.SoapServerProject.db.daoModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Reservation withId(Integer id) {
        setId(id);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Integer getUserId() {
        return user.getId();
    }
    public void setUserId(Integer userId) {
        this.user.setId(userId);
    }
    public Reservation withUserId(Integer userId) {
        setUserId(userId);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    public Integer getRoomId() {
        return room.getId();
    }
    public void setRoomId(Integer roomId) {
        this.room.setId(roomId);
    }
    public Reservation withRoomId(Integer roomId) {
        setRoomId(roomId);
        return this;
    }

    @Column
    private Date roomReservationFrom;

    public Date getReservationFrom() {
        return roomReservationFrom;
    }
    public void setReservationFrom(Date roomReservationFrom) {
        this.roomReservationFrom = roomReservationFrom;
    }
    public Reservation withReservationFrom(Date roomReservationFrom) {
        setReservationFrom(roomReservationFrom);
        return this;
    }

    @Column
    private Date roomReservationTo;

    public Date getRoomReservationTo() {
        return roomReservationTo;
    }
    public void setRoomReservationTo(Date roomReservationTo) {
        this.roomReservationTo = roomReservationTo;
    }
    public Reservation withReservationTo(Date roomReservationTo) {
        setRoomReservationTo(roomReservationTo);
        return this;
    }
}
