package com.springRest.RestAPI.repository;

import com.springRest.RestAPI.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

    boolean existsById(Tenant tenant);
    // Custom query methods can be added here if needed
    
}

