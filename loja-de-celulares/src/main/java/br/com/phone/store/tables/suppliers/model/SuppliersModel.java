package br.com.phone.store.tables.suppliers.model;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Entity class representing a supplier in the phone store system.
 *
 * <p>This class maps to the {@code suppliers} table and stores essential information
 * about each supplier, including their name, tax identification number (EIN), and active status.</p>
 */
@Table(name = "suppliers")
@Entity(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "supplierId")
public class SuppliersModel {

    /**
     * Unique identifier for the supplier.
     * Auto-generated using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suppliers_seq")
    @SequenceGenerator(name = "suppliers_seq", sequenceName = "suppliers_seq", allocationSize = 1)
    @Column(name = "supplier_id")
    private UUID supplierId;

    /**
     * Name of the supplier.
     * Cannot be null.
     */
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    /**
     * Supplier's Employer Identification Number (EIN) or equivalent tax ID.
     * Cannot be null.
     */
    @Column(name = "supplier_ein", nullable = false)
    private Long supplierEIN;

    /**
     * Indicates whether the supplier is currently active.
     * Defaults to {@code true} if not explicitly provided.
     */
    private Boolean active;

    /**
     * Constructs a {@code SuppliersModel} instance from a {@link RequestSuppliersDto}.
     *
     * @param dto DTO containing supplier registration data.
     */
    public SuppliersModel(RequestSuppliersDto dto) {
        this.supplierName = dto.supplierName();
        this.supplierEIN = dto.supplierEIN();
        this.active = dto.active() != null ? dto.active() : true;
    }
}
