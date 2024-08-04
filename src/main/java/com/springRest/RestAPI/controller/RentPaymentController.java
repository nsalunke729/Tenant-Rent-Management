package com.springRest.RestAPI.controller;

import com.springRest.RestAPI.entity.RentPayment;
import com.springRest.RestAPI.service.RentPaymentService;
import com.springRest.RestAPI.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentpayments")
public class RentPaymentController {

    private final RentPaymentService rentPaymentService;
    private final TenantService tenantService;

    @Autowired
    public RentPaymentController(RentPaymentService rentPaymentService, TenantService tenantService) {
        this.rentPaymentService = rentPaymentService;
        this.tenantService = tenantService;
    }

    // Get all rent payments
    @GetMapping
    public ResponseEntity<List<RentPayment>> getAllRentPayments() {
        List<RentPayment> rentPayments = rentPaymentService.getAllRentPayments();
        return new ResponseEntity<>(rentPayments, HttpStatus.OK);
    }

    // Get rent payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<RentPayment> getRentPaymentById(@PathVariable Integer id) {
        RentPayment rentPayment = rentPaymentService.getRentPaymentById(id);
        if (rentPayment != null) {
            return new ResponseEntity<>(rentPayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all rent payments for a specific tenant
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<RentPayment>> getRentPaymentsByTenantId(@PathVariable Integer tenantId) {
        if (!tenantService.existsById(tenantId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RentPayment> rentPayments = rentPaymentService.getRentPaymentsByTenantId(tenantId);
        return new ResponseEntity<>(rentPayments, HttpStatus.OK);
    }

    // Create a new rent payment
    @PostMapping
    public ResponseEntity<RentPayment> createRentPayment(@RequestBody RentPayment rentPayment) {
        if (!tenantService.existsById(rentPayment.getTenantId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentPayment createdRentPayment = rentPaymentService.saveRentPayment(rentPayment);
        return new ResponseEntity<>(createdRentPayment, HttpStatus.CREATED);
    }

    // Update an existing rent payment
    @PutMapping("/{id}")
    public ResponseEntity<RentPayment> updateRentPayment(@PathVariable Integer id, @RequestBody RentPayment rentPayment) {
        if (!rentPaymentService.getRentPaymentById(id).equals(rentPayment) || !tenantService.existsById(rentPayment.getTenantId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rentPayment.setId(id);
        RentPayment updatedRentPayment = rentPaymentService.saveRentPayment(rentPayment);
        return new ResponseEntity<>(updatedRentPayment, HttpStatus.OK);
    }

    // Delete a rent payment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentPayment(@PathVariable Integer id) {
        if (rentPaymentService.getRentPaymentById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rentPaymentService.deleteRentPayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
