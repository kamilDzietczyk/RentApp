package com.example.Rent.Repository;

import com.example.Rent.Models.ReservObject;
import com.example.Rent.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservObjectRepository extends JpaRepository<ReservObject,Integer> {

    @Query("SELECT COUNT(o) from object o Where o.Name = :data ")
    int findReservObject(@Param("data") String data);

    @Query("SELECT o.Cost from object o Where o.Name = :data ")
    int findCostReservObject(@Param("data") String data);


}
