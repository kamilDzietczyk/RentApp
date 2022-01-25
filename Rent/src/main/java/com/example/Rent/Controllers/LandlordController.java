package com.example.Rent.Controllers;


import com.example.Rent.Models.Landlord;
import com.example.Rent.Repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/Landlord")
public class LandlordController {

    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping
    @RequestMapping("/all")
    public List<Landlord> GetAll(){
        return landlordRepository.findAllLandlords();
    }
}
