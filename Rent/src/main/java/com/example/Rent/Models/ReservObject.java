package com.example.Rent.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "object")
public class ReservObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Object_ID;

    private String Name;
    private int Cost;
    private int Area;
    private String Description;

    public ReservObject(String name, int cost, int area, String description) {
        Name = name;
        Cost = cost;
        Area = area;
        Description = description;
    }

    public ReservObject() {

    }

    public int getObject_ID() {
        return Object_ID;
    }

    public void setObject_ID(int object_ID) {
        Object_ID = object_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
