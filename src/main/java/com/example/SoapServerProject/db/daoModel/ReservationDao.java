package com.example.SoapServerProject.db.daoModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class ReservationDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ReservationDao withId(Integer id) {
        setId(id);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "accountId")
    private AccountDao accountDao;

    public Integer getAccountId() {
        return accountDao.getId();
    }
    public void setAccountId(Integer accountId) {
        this.accountDao.setId(accountId);
    }
    public ReservationDao withAccountId(Integer accountId) {
        setAccountId(accountId);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomDao roomDao;

    public Integer getRoomId() {
        return roomDao.getId();
    }
    public void setRoomId(Integer roomId) {
        this.roomDao.setId(roomId);
    }
    public ReservationDao withRoomId(Integer roomId) {
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
    public ReservationDao withReservationFrom(Date roomReservationFrom) {
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
    public ReservationDao withReservationTo(Date roomReservationTo) {
        setRoomReservationTo(roomReservationTo);
        return this;
    }
}
