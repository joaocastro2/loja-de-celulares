package br.com.phone.store.stock.model;

import br.com.phone.store.stock.dto.RequestStockDto;
import br.com.phone.store.suppliers.model.SuppliersModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    private UUID productId;
    @Column(name = "product_name")
    private String productName;
    private Integer price_in_cents;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "fk_supplier_id") // nome da coluna estrangeira no banco
    private SuppliersModel supplierId;
    private Boolean active;

    public StockModel(RequestStockDto dto, SuppliersModel supplier) {
        this.productName = dto.product_name();
        this.price_in_cents = dto.price_in_cents();
        this.amount = dto.amount() != null ? dto.amount() : 0;
        this.active = dto.active() != null ? dto.active() : true;
        this.supplierId = supplier;
    }
}

