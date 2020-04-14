package com.example.SoapServerProject.db.daoModel;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Hotel withId(Integer id) {
        setId(id);
        return this;
    }

    @Column
    private String hotelName;

    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public Hotel withHotelName(String hotelName) {
        setHotelName(hotelName);
        return this;
    }

    @Column
    private String hotelDescription;

    public String getHotelDescription() {
        return hotelDescription;
    }
    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }
    public Hotel withHotelDescription(String hotelDescription) {
        setHotelDescription(hotelDescription);
        return this;
    }

    @Column
    private String hotelImagePath;

    public String getHotelImagePath() {
        return hotelImagePath;
    }
    public void setHotelImagePath(String hotelImagePath) {
        this.hotelImagePath = hotelImagePath;
    }
    public Hotel withHotelImagePath(String hotelImagePath) {
        setHotelImagePath(hotelImagePath);
        return this;
    }
}