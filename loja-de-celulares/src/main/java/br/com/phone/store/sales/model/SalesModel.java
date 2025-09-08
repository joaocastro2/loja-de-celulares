package br.com.phone.store.sales.model;

import br.com.phone.store.customers.model.CustomersModel;
import br.com.phone.store.sales.dto.RequestSalesDto;
import br.com.phone.store.sellers.model.SellersModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sale_id")
public class SalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq")
    @SequenceGenerator(name = "sales_seq", sequenceName = "sales_seq", allocationSize = 0)
    @Column(name = "sale_id")
    private Integer saleId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomersModel customerId;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellersModel sellerId;

    @Column(name = "sale_date", nullable = false)
    private LocalDate date;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @OneToMany(mappedBy = "salesId")
    private List<SalesModel> salesId;

    public SalesModel (RequestSalesDto dto, CustomersModel customer, SellersModel seller){
        this.customerId = customer;
        this.sellerId = seller;
        this.date = dto.date();
        this.totalAmount = dto.totalAmount();
    }

}
