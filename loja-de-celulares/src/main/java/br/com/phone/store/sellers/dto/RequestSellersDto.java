package br.com.phone.store.sellers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSellersDto(

        @NotBlank String sellerName,
        @NotNull String sellerSsn,
        @NotBlank String sellerEmail,
        Double sellerComRate,
        Boolean active
){}
