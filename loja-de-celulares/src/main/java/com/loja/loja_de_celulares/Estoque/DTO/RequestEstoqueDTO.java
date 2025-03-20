package com.loja.loja_de_celulares.Estoque.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Contém as informações que serão enviadas pelo usuário
public record RequestEstoqueDTO(

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String nome_produto,

        @NotNull(message = "O preço em centavos é obrigatório.")
        Integer preco_em_centavos,

        Boolean ativo){
}
