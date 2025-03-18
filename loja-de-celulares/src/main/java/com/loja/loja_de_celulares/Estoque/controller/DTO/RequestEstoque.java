package com.loja.loja_de_celulares.Estoque.controller.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestEstoque(

        @NotBlank(message = "O código do produto não pode estar vazio.")
        String codigo_produto,

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String nome_produto,

        @NotNull(message = "O preço em centavos é obrigatório.")
        Integer preco_em_centavos,

        Boolean ativo){
}
