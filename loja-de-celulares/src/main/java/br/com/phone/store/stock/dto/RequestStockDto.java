package br.com.phone.store.stock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Contém as informações que serão enviadas pelo usuário
public record RequestStockDto(

        String codigo_produto,

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String nome_produto,

        @NotNull(message = "O preço em centavos é obrigatório.")
        Integer preco_em_centavos,

        Boolean ativo){
}
