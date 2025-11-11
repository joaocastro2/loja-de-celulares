package br.com.phone.store.tables.stock.model;

import br.com.phone.store.tables.stock.dto.RequestStockDto;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity class representing a product in the stock system of the phone store.
 *
 * <p>This class maps to the {@code stock} table and stores information about
 * each product, including its name, price in cents, quantity, supplier, and active status.</p>
 *
 * <p>It establishes a many-to-one relationship with {@link SuppliersModel},
 * linking each product to its supplier.</p>
 */
@Table(name = "stock")
@Entity(name = "stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "productId")
public class StockModel {

    /**
     * Unique identifier for the product.
     * Auto-generated using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    /**
     * Name of the product.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * Price of the product expressed in cents.
     */
    private Integer price_in_cents;

    /**
     * Quantity of the product available in stock.
     */
    private Integer amount;

    /**
     * Reference to the supplier providing the product.
     * Mapped by the foreign key {@code fk_supplier_id}.
     */
    @ManyToOne
    @JoinColumn(name = "fk_supplier_id")
    private SuppliersModel supplierId;

    /**
     * Indicates whether the product is currently active.
     */
    private Boolean active;

    /**
     * Constructs a {@code StockModel} instance from a {@link RequestStockDto} and supplier.
     *
     * @param dto DTO containing product registration data.
     * @param supplier Supplier entity associated with the product.
     */
    public StockModel(RequestStockDto dto, SuppliersModel supplier) {
        this.productName = dto.product_name();
        this.price_in_cents = dto.price_in_cents();
        this.amount = dto.amount() != null ? dto.amount() : 0;
        this.active = dto.active() != null ? dto.active() : true;
        this.supplierId = supplier;
    }
}
