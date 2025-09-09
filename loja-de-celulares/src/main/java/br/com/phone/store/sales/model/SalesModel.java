package br.com.phone.store.sales.model;

import br.com.phone.store.customers.model.CustomersModel;
import br.com.phone.store.sale_items.model.SaleItemsModel;
import br.com.phone.store.sellers.model.SellersModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "saleId")
public class SalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq")
    @SequenceGenerator(name = "sales_seq", sequenceName = "sales_seq", allocationSize = 1)
    @Column(name = "sale_id")
    private Integer saleId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomersModel customer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellersModel seller;

    @Column(name = "sale_date", nullable = false)
    private LocalDate date;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemsModel> items = new ArrayList<>();

    public SalesModel(CustomersModel customer, SellersModel seller) {
        this.customer = customer;
        this.seller = seller;
        this.date = LocalDate.now();
        this.totalAmount = 0.0; // será atualizado no service
    }
}
