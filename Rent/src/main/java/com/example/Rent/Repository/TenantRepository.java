package com.example.Rent.Repository;

import com.example.Rent.Models.Landlord;
import com.example.Rent.Models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant,Integer> {

    @Query("SELECT count (t) FROM tenant t Where t.Name=:data")
    int findTenantByName(@Param(value = "data") String data);

}
