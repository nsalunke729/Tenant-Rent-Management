package com.springRest.RestAPI.service;

import com.springRest.RestAPI.entity.Tenant;
import com.springRest.RestAPI.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    // Fetch all tenants
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    // Fetch a tenant by ID
    public Tenant getTenantById(Integer id) {
        return tenantRepository.findById(id).orElse(null);
    }

    // Create or update a tenant
    public Tenant saveTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    // Delete a tenant by ID
    public void deleteTenant(Integer id) {
        tenantRepository.deleteById(id);
    }
    
    // Check if a tenant exists by ID
    public boolean existsById(Integer tenantId) {
        return tenantRepository.existsById(tenantId);
    }
}
