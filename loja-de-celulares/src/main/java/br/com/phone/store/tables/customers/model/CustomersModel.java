package br.com.phone.store.tables.customers.model;

import br.com.phone.store.tables.customers.dto.RequestCustomersDto;
import br.com.phone.store.tables.sales.model.SalesModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity class representing a customer in the phone store system.
 *
 * <p>This class maps to the {@code customers} table in the database and contains
 * customer-related attributes such as name, SSN, email, and phone number.</p>
 *
 * <p>It also establishes a one-to-many relationship with {@link SalesModel},
 * indicating that a customer can be associated with multiple sales records.</p>
 */
@Table(name = "customers")
@Entity(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerId")
public class CustomersModel {

    /**
     * Unique identifier for the customer.
     * Auto-generated using a sequence named {@code customer_seq}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 0)
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * Full name of the customer.
     * Cannot be null.
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    /**
     * Customer's identification number.
     * Cannot be null.
     */
    @Column(name = "customer_cpf", nullable = false)
    private String customerCpf;

    /**
     * Customer's email address.
     * Cannot be null.
     */
    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    /**
     * Customer's phone number.
     * Cannot be null.
     */
    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    /**
     * List of sales associated with this customer.
     * Mapped by the {@code customerId} field in {@link SalesModel}.
     */
    @OneToMany(mappedBy = "customerId")
    private List<SalesModel> sales;

    /**
     * Constructs a {@code CustomersModel} instance from a {@link RequestCustomersDto}.
     *
     * @param dto DTO containing customer registration data.
     */
    public CustomersModel(RequestCustomersDto dto){
        this.customerName = dto.customerName();
        this.customerCpf = dto.customerCpf();
        this.customerEmail = dto.customerEmail();
        this.customerPhone = dto.customerPhone();
    }
}
