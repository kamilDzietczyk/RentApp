package com.example.Rent.Repository;

import com.example.Rent.Models.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LandlordRepository extends JpaRepository<Landlord,Integer> {

    @Query("SELECT l FROM landlord l")
    List<Landlord> findAllLandlords();
}
