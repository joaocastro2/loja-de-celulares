package com.loja.loja_de_celulares.Estoque.model;

import com.loja.loja_de_celulares.Estoque.controller.DTO.RequestEstoque;
import jakarta.persistence.*;
import lombok.*;

@Table(name="estoque")
@Entity(name="estoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codigo_produto")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo_produto;
    private String nome_produto;
    private Integer preco_em_centavos;
    private Boolean ativo;

    public Estoque(RequestEstoque requestEstoque) {
        this.nome_produto = requestEstoque.nome_produto();
        this.preco_em_centavos = requestEstoque.preco_em_centavos();
        this.ativo = true;
    }
}

