package com.springRest.tenantRent.controller;

import com.springRest.tenantRent.entity.Tenant;
import com.springRest.tenantRent.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    // Get all tenants
    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        System.out.println("in getTenant method"+ "http://127.0.0.1");    
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    // Get tenant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Integer id) {
        Tenant tenant = tenantService.getTenantById(id);
        if (tenant != null) {
            return new ResponseEntity<>(tenant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new tenant
    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        Tenant createdTenant = tenantService.saveTenant(tenant);
        return new ResponseEntity<>(createdTenant, HttpStatus.CREATED);
    }

    // Update an existing tenant
    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Integer id, @RequestBody Tenant tenant) {
        if (!tenantService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tenant.setId(id);
        Tenant updatedTenant = tenantService.saveTenant(tenant);
        return new ResponseEntity<>(updatedTenant, HttpStatus.OK);
    }

    // Delete a tenant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Integer id) {
        if (!tenantService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tenantService.deleteTenant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
