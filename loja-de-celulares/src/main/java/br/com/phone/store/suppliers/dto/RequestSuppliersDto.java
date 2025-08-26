package br.com.phone.store.suppliers.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RequestSuppliersDto(
        @NotBlank String supplierName,
        @NotBlank String supplierEIN,
        Boolean active
) {}