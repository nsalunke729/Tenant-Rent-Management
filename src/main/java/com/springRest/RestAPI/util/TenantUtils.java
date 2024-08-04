package com.springRest.RestAPI.util;

import com.springRest.RestAPI.entity.Tenant;
import com.springRest.RestAPI.dto.TenantDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for handling Tenant-related operations.
 */
public class TenantUtils {

    /**
     * Converts a Tenant entity to a TenantDto.
     * 
     * @param tenant The Tenant entity to be converted.
     * @return A TenantDto representing the Tenant entity.
     */
    public static TenantDto convertToDto(Tenant tenant) {
        if (tenant == null) {
            return null;
        }
        
        TenantDto dto = new TenantDto();
        dto.setId(tenant.getId());
        dto.setName(tenant.getName());
        dto.setRoomNo(tenant.getRoomNo());
        dto.setRent(tenant.getRent());
        dto.setContact(tenant.getContact());
        dto.setEndDate(tenant.getEndDate());
        dto.setStartDate(tenant.getStartDate());
        
        return dto;
    }

    /**
     * Converts a list of Tenant entities to a list of TenantDto.
     * 
     * @param tenants The list of Tenant entities.
     * @return A list of TenantDto representing the Tenant entities.
     */
    public static List<TenantDto> convertToDtoList(List<Tenant> tenants) {
        return tenants.stream()
                .map(TenantUtils::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total rent for a list of Tenant entities.
     * 
     * @param tenants The list of Tenant entities.
     * @return The total rent sum.
     */
    public static int calculateTotalRent(List<Tenant> tenants) {
        return tenants.stream()
                .mapToInt(Tenant::getRent)
                .sum();
    }
}
