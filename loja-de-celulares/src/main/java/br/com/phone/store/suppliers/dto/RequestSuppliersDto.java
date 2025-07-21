package br.com.phone.store.suppliers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSuppliersDto(



        @NotBlank(message = "O nome do fornecedor é obrigatório.")
        String supplierName,

        @NotBlank(message = "O CNPJ/EIN do fornecedor é obrigatório.")
        String supplierEIN,

        @NotNull(message = "O status de atividade é obrigatório.")
        Boolean active){
}
