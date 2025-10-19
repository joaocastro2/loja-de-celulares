package br.com.phone.store.tables.suppliers.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestSuppliersDto(
        @NotBlank String supplierName,
        @NotNull Long supplierEIN,
        Boolean active
) {}