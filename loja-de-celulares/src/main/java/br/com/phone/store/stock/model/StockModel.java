package br.com.phone.store.stock.model;

import br.com.phone.store.stock.dto.RequestStockDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name="stock")
@Entity(name="stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "productId")
//Defines the data and relates it to the database
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_name")
    private String productName;
    private Integer price_in_cents;
    private Boolean active;

    public StockModel(RequestStockDto requestStockDto) {
        this.productId = requestStockDto.product_name();
        this.price_in_cents = requestStockDto.price_in_cents();
        this.active = true;
    }
}

