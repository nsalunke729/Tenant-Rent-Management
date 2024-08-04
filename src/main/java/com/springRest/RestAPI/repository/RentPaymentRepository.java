package com.springRest.RestAPI.repository;

import com.springRest.RestAPI.entity.RentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentPaymentRepository extends JpaRepository<RentPayment, Integer> {
    // Custom query methods can be added here if needed
    
}