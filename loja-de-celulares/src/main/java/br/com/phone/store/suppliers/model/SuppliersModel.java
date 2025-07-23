package br.com.phone.store.suppliers.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="suppliers")
@Entity(name="suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "supplier_id")
public class SuppliersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "supplier_id")
    private String supplierId;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "supplier_ein")
    private String supplierEIN;
    private Boolean active;
}

//teste