package com.example.Rent.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    private String Object_Name;
    private Date Start_Reservation;
    private Date End_Reservation;
    private String Tenant;
    private long Cost;

    public Reservation(String object_Name, Date start_Reservation, Date end_Reservation, String tenant, long cost) {
        Object_Name = object_Name;
        Start_Reservation = start_Reservation;
        End_Reservation = end_Reservation;
        Tenant = tenant;
        Cost = cost;
    }

    public Reservation() {

    }

    public String getObject_Name() {
        return Object_Name;
    }

    public void setObject_Name(String object_Name) {
        Object_Name = object_Name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getStart_Reservation() {
        return Start_Reservation;
    }

    public void setStart_Reservation(Date start_Reservation) {
        Start_Reservation = start_Reservation;
    }

    public Date getEnd_Reservation() {
        return End_Reservation;
    }

    public void setEnd_Reservation(Date end_Reservation) {
        End_Reservation = end_Reservation;
    }

    public String getTenant() {
        return Tenant;
    }

    public void setTenant(String tenant) {
        Tenant = tenant;
    }

    public long getCost() {
        return Cost;
    }

    public void setCost(long cost) {
        Cost = cost;
    }
}
