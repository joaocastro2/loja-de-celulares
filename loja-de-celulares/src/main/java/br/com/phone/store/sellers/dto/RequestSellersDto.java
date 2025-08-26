package br.com.phone.store.sellers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSellersDto(

        @NotBlank String sellerName,
        @NotBlank String sellerSSN,
        @NotBlank String sellerEmail,
        Double sellerComRate,
        Boolean active
){}
