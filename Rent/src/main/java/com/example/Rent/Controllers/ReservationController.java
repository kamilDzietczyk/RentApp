package com.example.Rent.Controllers;


import com.example.Rent.Models.Reservation;
import com.example.Rent.Models.Tenant;
import com.example.Rent.Repository.ReservObjectRepository;
import com.example.Rent.Repository.ReservationRepository;
import com.example.Rent.Repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservObjectRepository reservObjectRepository;
    @Autowired
    TenantRepository tenantRepository;

    @GetMapping
    @RequestMapping("getByTent/{tentent}")
    public List<Reservation> GetByTent(@PathVariable(value = "tentent") String tent) {
        return reservationRepository.findReservationByTent(tent);
    }

    @GetMapping
    @RequestMapping("getByObjName/{object_name}")
    public List<Reservation> GetByObjName(@PathVariable(value = "object_name") String object_name) {
        return reservationRepository.findReservationByObjectName(object_name);
    }

    @PostMapping
    @RequestMapping("/AddReservation/Get/{start_data}/{end_data}/{ObjectName}/{Tentant}")
    public String AddReservation(@PathVariable(value = "start_data") String start_data,@PathVariable(value = "end_data") String end_data,
                                 @PathVariable(value = "ObjectName") String data1,@PathVariable(value = "Tentant") String tentant) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date s_date = simpleDateFormat.parse(start_data);
        Date e_date = simpleDateFormat.parse(end_data);
        int reservation_exist = reservationRepository.IfExist(s_date,e_date,data1);
        int object_name = reservObjectRepository.findReservObject(data1);

        if(object_name >= 1 && reservation_exist != 0){
            return "Error invalid date, or objectName";
        }

        if(tenantRepository.findTenantByName(tentant)==0){
            Tenant t = new Tenant(tentant);
            tenantRepository.save(t);
        }

        int object_cost = reservObjectRepository.findCostReservObject(data1);
        long daysBetween = ChronoUnit.DAYS.between(s_date.toInstant(),e_date.toInstant());
        Reservation r = new Reservation(data1,s_date,e_date,tentant,daysBetween*object_cost);
        reservationRepository.save(r);
        return "Reservation is add ";
      }

    @PostUpdate
    @RequestMapping("/UpdateReservation/{id}/{start_data}/{end_data}/{ObjectName}/{Tentant}")
    public String UpdateReservation(@PathVariable(value = "id") int id,
                                         @PathVariable(value = "start_data") String start_data,
                                         @PathVariable(value = "end_data") String end_data,
                                         @PathVariable(value = "ObjectName") String data1,
                                         @PathVariable(value = "Tentant") String tentant) throws ParseException {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date s_date = simpleDateFormat.parse(start_data);
        Date e_date = simpleDateFormat.parse(end_data);

        if(reservationRepository.findReservationByID(id)==0){
            return "Not found reservation";
        }
        Optional<Reservation> r = reservationRepository.findById(id);
        r.get().setStart_Reservation(s_date);
        r.get().setEnd_Reservation(e_date);
        r.get().setObject_Name(data1);
        r.get().setTenant(tentant);
        r.get().setCost(reservObjectRepository.findCostReservObject(data1)*ChronoUnit.DAYS.between(s_date.toInstant(),e_date.toInstant()));

        if(reservationRepository.IfExist(s_date,e_date,r.get().getObject_Name())!=0){
            return "Reservation is exist";
        }
        reservationRepository.save(r.get());
        return "Reservation is updated";
    }

}
