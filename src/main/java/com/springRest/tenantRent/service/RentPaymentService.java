package com.springRest.tenantRent.service;

import com.springRest.tenantRent.entity.RentPayment;
import java.util.Collections;
import com.springRest.tenantRent.repository.RentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentPaymentService {

    private final RentPaymentRepository rentPaymentRepository;

    @Autowired
    public RentPaymentService(RentPaymentRepository rentPaymentRepository) {
        this.rentPaymentRepository = rentPaymentRepository;
    }

    // Fetch all rent payments
    public List<RentPayment> getAllRentPayments() {
        return rentPaymentRepository.findAll();
    }

    // Fetch rent payment by ID
    public RentPayment getRentPaymentById(Integer id) {
        return rentPaymentRepository.findById(id).orElse(null);
    }

    // Create or update a rent payment
    public RentPayment saveRentPayment(RentPayment rentPayment) {
        return rentPaymentRepository.save(rentPayment);
    }

    // Delete a rent payment by ID
    public void deleteRentPayment(Integer id) {
        rentPaymentRepository.deleteById(id);
    }
    
    // Fetch all rent payments by tenant ID
    public List<RentPayment> getRentPaymentsByTenantId(Integer tenantId) {
        Optional<RentPayment> rentPayment = rentPaymentRepository.findById(tenantId);
        return rentPayment.map(Collections::singletonList)
                          .orElse(Collections.emptyList());
    }
}
