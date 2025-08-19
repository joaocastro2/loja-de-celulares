package br.com.phone.store.stock.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Contains the information that will be sent by the user
public record RequestStockDto(

        String product_id,

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String product_name,

        @NotNull(message = "O preço em centavos é obrigatório.")
        Integer price_in_cents,

        Integer amount,

        @NotBlank(message = "O ID do fornecedor é obrigatório.")
        String supplier_id,

        Boolean active){

}
