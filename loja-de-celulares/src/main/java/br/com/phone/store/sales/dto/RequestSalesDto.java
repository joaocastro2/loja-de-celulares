package br.com.phone.store.sales.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record RequestSalesDto(

        Integer customerId,
        Integer sellerId,
        LocalDate date,
        Double totalAmount
){}
