package br.com.phone.store.tables.customers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) used to receive customer registration information.
 *
 * <p>This record encapsulates the essential data required to create or update
 * a customer in the system.</p>
 *
 * <p>Validation annotations ensure that mandatory fields are properly filled
 * before processing.</p>
 *
 * @param customerName Full name of the customer. Must not be blank.
 * @param customerSsn Customer's identification number. Must not be null.
 * @param customerEmail Customer's email address. Must not be blank.
 * @param customerPhone Customer's phone number. Must not be null.
 */
public record RequestCustomersDto(

        @NotBlank String customerName,
        @NotNull String customerSsn,
        @NotBlank String customerEmail,
        @NotNull String customerPhone
) {}
