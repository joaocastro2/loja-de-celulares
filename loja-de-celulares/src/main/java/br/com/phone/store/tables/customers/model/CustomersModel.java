package br.com.phone.store.tables.customers.model;

import br.com.phone.store.tables.customers.dto.RequestCustomersDto;
import br.com.phone.store.tables.sales.model.SalesModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//CUSTOMER CLASS "SETTINGS"
@Table(name = "customers")
@Entity(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerId")
//CUSTOMER CLASS
public class CustomersModel {

    //CUSTOMER OBJECT ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 0)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_ssn", nullable = false)
    private String customerSsn;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @OneToMany(mappedBy = "customerId")
    private List<SalesModel> sales;

    //CLASS CONSTRUCTOR
    public CustomersModel(RequestCustomersDto dto){
        this.customerName = dto.customerName();
        this.customerSsn = dto.customerSsn();
        this.customerEmail = dto.customerEmail();
        this.customerPhone = dto.customerPhone();
    }

}