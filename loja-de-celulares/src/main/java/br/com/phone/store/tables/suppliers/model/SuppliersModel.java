package br.com.phone.store.tables.suppliers.model;

import br.com.phone.store.tables.suppliers.dto.RequestSuppliersDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Table(name="suppliers")
@Entity(name="suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "supplierId")
public class SuppliersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "supplier_id")
    private UUID supplierId;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "supplier_ein", nullable = false)
    private Long supplierEIN;

    private Boolean active;

    public SuppliersModel(RequestSuppliersDto dto) {
        this.supplierName = dto.supplierName();
        this.supplierEIN = dto.supplierEIN();
        this.active = dto.active() != null ? dto.active() : true; // default = true
    }
}
