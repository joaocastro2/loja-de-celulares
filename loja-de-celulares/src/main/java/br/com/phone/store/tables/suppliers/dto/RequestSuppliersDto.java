package br.com.phone.store.tables.suppliers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) used to receive supplier registration details.
 *
 * <p>This record encapsulates the necessary information to create or update a supplier
 * in the phone store system, including the supplier's name, tax identification number,
 * and active status.</p>
 *
 * <p>Validation annotations ensure that required fields are properly filled before processing.</p>
 *
 * @param supplierName Name of the supplier. Must not be blank.
 * @param supplierCnpj Employer Identification Number (EIN) or equivalent tax ID. Must not be null.
 * @param active Indicates whether the supplier is currently active (optional).
 */
public record RequestSuppliersDto(
        @NotBlank String supplierName,
        @NotNull Long supplierCnpj,
        Boolean active
) {}
