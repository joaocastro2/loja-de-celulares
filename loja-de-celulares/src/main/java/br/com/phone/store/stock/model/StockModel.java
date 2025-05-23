package br.com.phone.store.stock.model;

import br.com.phone.store.stock.dto.RequestStockDto;
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
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo_produto;
    private String nome_produto;
    private Integer preco_em_centavos;
    private Boolean ativo;

    public StockModel(RequestStockDto requestStockDto) {
        this.nome_produto = requestStockDto.nome_produto();
        this.preco_em_centavos = requestStockDto.preco_em_centavos();
        this.ativo = true;
    }
}

