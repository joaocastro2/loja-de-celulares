package br.com.phone.store.tables.sale_items.model;

import br.com.phone.store.tables.sale_items.dto.RequestSaleItemsDto;
import br.com.phone.store.tables.sales.model.SalesModel;
import br.com.phone.store.tables.stock.model.StockModel;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an individual item included in a sales transaction.
 *
 * <p>This class maps to the {@code sale_items} table and stores details about
 * the product sold, quantity, unit price, and subtotal for each item in a sale.</p>
 *
 * <p>It establishes many-to-one relationships with both {@link SalesModel} and {@link StockModel},
 * linking each sale item to its corresponding sale and product.</p>
 */
@Table(name = "sale_items")
@Entity(name = "sale_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "saleItemsId")
public class SaleItemsModel {

    /**
     * Unique identifier for the sale item.
     * Auto-generated using a sequence named {@code saleitems_seq}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saleitems_seq")
    @SequenceGenerator(name = "saleitems_seq", sequenceName = "saleitems_seq", allocationSize = 1)
    @Column(name = "saleitems_id")
    private Integer saleItemsId;

    /**
     * Reference to the sale this item belongs to.
     * Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SalesModel sales;

    /**
     * Reference to the product being sold.
     * Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private StockModel productId;

    /**
     * Quantity of the product sold.
     */
    @Column(name = "saleitems_qtty")
    private Integer saleItemsQtty;

    /**
     * Unit price of the product at the time of sale.
     * Cannot be null.
     */
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    /**
     * Subtotal for this sale item (unit price × quantity).
     * Cannot be null.
     */
    @Column(name = "saleitems_subtotal", nullable = false)
    private Double saleItemsSubtotal;

    /**
     * Constructs a {@code SaleItemsModel} instance from a {@link RequestSaleItemsDto},
     * linking it to the given sale and product, and calculating the subtotal.
     *
     * @param dto DTO containing sale item details.
     * @param sales Associated sale.
     * @param product Associated product.
     * @param unitPrice Unit price of the product.
     */
    public SaleItemsModel(RequestSaleItemsDto dto, SalesModel sales, StockModel product, Double unitPrice) {
        this.sales = sales;
        this.productId = product;
        this.saleItemsQtty = dto.saleItemsQtty();
        this.unitPrice = unitPrice;
        this.saleItemsSubtotal = unitPrice * this.saleItemsQtty;
    }
}
