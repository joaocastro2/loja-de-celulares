package com.loja.loja_de_celulares.Estoque.model;

import com.loja.loja_de_celulares.Estoque.DTO.RequestEstoqueDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name="estoque")
@Entity(name="estoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codigo_produto")
//Define os dados e os relaciona ao banco
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo_produto;
    private String nome_produto;
    private Integer preco_em_centavos;
    private Boolean ativo;

    public Estoque(RequestEstoqueDTO requestEstoqueDTO) {
        this.nome_produto = requestEstoqueDTO.nome_produto();
        this.preco_em_centavos = requestEstoqueDTO.preco_em_centavos();
        this.ativo = true;
    }
}

