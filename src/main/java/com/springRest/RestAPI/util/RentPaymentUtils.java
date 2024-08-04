package com.springRest.RestAPI.util;

import com.springRest.RestAPI.entity.RentPayment;
import com.springRest.RestAPI.dto.RentPaymentDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for handling RentPayment-related operations.
 */
public class RentPaymentUtils {

    /**
     * Converts a RentPayment entity to a RentPaymentDto.
     * 
     * @param rentPayment The RentPayment entity to be converted.
     * @return A RentPaymentDto representing the RentPayment entity.
     */
    public static RentPaymentDto convertToDto(RentPayment rentPayment) {
        if (rentPayment == null) {
            return null;
        }
        
        RentPaymentDto dto = new RentPaymentDto();
        dto.setId(rentPayment.getId());
        dto.setTenantId(rentPayment.getTenant().getId());
        dto.setAmount(rentPayment.getAmount());
        dto.setPaymentDate(rentPayment.getPaymentDate());
        dto.setPaymentMethod(rentPayment.getPaymentMethod());
        
        return dto;
    }

    /**
     * Converts a list of RentPayment entities to a list of RentPaymentDto.
     * 
     * @param rentPayments The list of RentPayment entities.
     * @return A list of RentPaymentDto representing the RentPayment entities.
     */
    public static List<RentPaymentDto> convertToDtoList(List<RentPayment> rentPayments) {
        return rentPayments.stream()
                .map(RentPaymentUtils::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total amount of all rent payments.
     * 
     * @param rentPayments The list of RentPayment entities.
     * @return The total amount sum.
     */
    public static int calculateTotalAmount(List<RentPayment> rentPayments) {
        return rentPayments.stream()
                .mapToInt(RentPayment::getAmount)
                .sum();
    }
}
