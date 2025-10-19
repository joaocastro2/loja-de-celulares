package br.com.phone.store.tables.customers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCustomersDto(

        @NotBlank String customerName,
        @NotNull String customerSsn,
        @NotBlank String customerEmail,
        @NotNull String customerPhone
){}
