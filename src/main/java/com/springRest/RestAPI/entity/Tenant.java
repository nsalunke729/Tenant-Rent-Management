package com.springRest.RestAPI.entity;
import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "roomno", nullable = false)
    private Integer roomNo;

    @Column(name = "rent", nullable = false)
    private Integer rent;

    @Column(name = "contact", nullable = false)
    private Long contact;

    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "startdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    // Constructors, getters, and setters

    public Tenant() {
    }

    public Tenant(Integer id, String name, Integer roomNo, Integer rent, Long contact, Date endDate, Date startDate) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.rent = rent;
        this.contact = contact;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
