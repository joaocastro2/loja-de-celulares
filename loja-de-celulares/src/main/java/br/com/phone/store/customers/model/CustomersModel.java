package br.com.phone.store.customers.model;

import br.com.phone.store.customers.dto.RequestCustomersDto;
import br.com.phone.store.sellers.dto.RequestSellersDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "customers")
@Entity(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerId")
public class CustomersModel {

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

    public CustomersModel(RequestCustomersDto dto){
        this.customerName = dto.customerName();
        this.customerSsn = dto.customerSsn();
        this.customerEmail = dto.customerEmail();
        this.customerPhone = dto.customerPhone();
    }

}