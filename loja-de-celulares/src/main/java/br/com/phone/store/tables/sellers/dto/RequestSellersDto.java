package br.com.phone.store.tables.sellers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) used to receive seller registration details.
 *
 * <p>This record encapsulates the necessary information to create or update a seller
 * in the phone store system, including personal and professional attributes.</p>
 *
 * <p>Validation annotations ensure that required fields are properly filled before processing.</p>
 *
 * @param sellerName Full name of the seller. Must not be blank.
 * @param sellerCpf Seller's identification number (e.g., CPF). Must not be null.
 * @param sellerEmail Seller's email address. Must not be blank.
 * @param sellerComRate Seller's commission rate (optional).
 * @param active Indicates whether the seller is currently active (optional).
 */
public record RequestSellersDto(

        @NotBlank String sellerName,
        @NotNull String sellerCpf,
        @NotBlank String sellerEmail,
        Double sellerComRate,
        Boolean active
) {}
