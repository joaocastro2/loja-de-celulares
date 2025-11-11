package br.com.phone.store.tables.sales.model;

import br.com.phone.store.tables.customers.model.CustomersModel;
import br.com.phone.store.tables.sale_items.model.SaleItemsModel;
import br.com.phone.store.tables.sellers.model.SellersModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a sales transaction in the phone store system.
 *
 * <p>This class maps to the {@code sales} table and stores information about
 * the customer, seller, sale date, total amount, and associated sale items.</p>
 *
 * <p>It establishes relationships with {@link CustomersModel}, {@link SellersModel},
 * and a list of {@link SaleItemsModel} to fully represent a sale.</p>
 */
@Entity
@Table(name = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "saleId")
public class SalesModel {

    /**
     * Unique identifier for the sale.
     * Auto-generated using a sequence named {@code sales_seq}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq")
    @SequenceGenerator(name = "sales_seq", sequenceName = "sales_seq", allocationSize = 1)
    @Column(name = "sale_id")
    private Integer saleId;

    /**
     * Reference to the customer who made the purchase.
     * Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomersModel customerId;

    /**
     * Reference to the seller who processed the sale.
     * Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellersModel seller;

    /**
     * Date when the sale was made.
     * Automatically set to the current date.
     */
    @Column(name = "sale_date", nullable = false)
    private LocalDate date;

    /**
     * Total amount of the sale.
     * Initially set to 0.0 and updated during processing.
     */
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    /**
     * List of items included in the sale.
     * Cascade operations ensure items are persisted and removed with the sale.
     */
    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemsModel> items = new ArrayList<>();

    /**
     * Constructs a {@code SalesModel} with the specified customer and seller.
     * Initializes the sale date to the current date and total amount to zero.
     *
     * @param customer The customer making the purchase.
     * @param seller The seller handling the transaction.
     */
    public SalesModel(CustomersModel customer, SellersModel seller) {
        this.customerId = customer;
        this.seller = seller;
        this.date = LocalDate.now();
        this.totalAmount = 0.0; // will be updated in the service layer
    }
}
