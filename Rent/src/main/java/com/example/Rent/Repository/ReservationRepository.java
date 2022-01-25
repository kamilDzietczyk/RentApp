package com.example.Rent.Repository;

import com.example.Rent.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("SELECT r FROM reservation r where  r.Tenant=:tentent")
    List<Reservation> findReservationByTent(@Param("tentent") String tentent);

    @Query("SELECT r FROM reservation r where  r.Object_Name=:object_name")
    List<Reservation> findReservationByObjectName(@Param("object_name") String obj);

    @Query("SELECT Count(r) FROM reservation r where  r.ID=:id")
    int findReservationByID(@Param("id") int id);

    @Query("SELECT COUNT(r) from reservation r Where ((:data between r.Start_Reservation and r.End_Reservation) or (:data1 between r.Start_Reservation and r.End_Reservation)) and r.Object_Name=:data2")
    int IfExist(@Param("data") Date data,@Param("data1") Date data1, @Param("data2") String name);

}
