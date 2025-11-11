package br.com.phone.store.tables.sellers.model;

import br.com.phone.store.tables.sales.model.SalesModel;
import br.com.phone.store.tables.sellers.dto.RequestSellersDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a seller in the phone store system.
 *
 * <p>This class maps to the {@code sellers} table and stores personal and professional
 * information about each seller, including their commission rate and active status.</p>
 *
 * <p>It also establishes a one-to-many relationship with {@link SalesModel}, indicating
 * that a seller can be associated with multiple sales transactions.</p>
 */
@Table(name = "sellers")
@Entity(name = "sellers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sellerId")
public class SellersModel {

    /**
     * Unique identifier for the seller.
     * Auto-generated using a sequence named {@code seller_seq}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_seq")
    @SequenceGenerator(name = "seller_seq", sequenceName = "seller_seq", allocationSize = 1)
    @Column(name = "seller_id")
    private Integer sellerId;

    /**
     * Full name of the seller.
     * Cannot be null.
     */
    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    /**
     * Seller's identification number (e.g., CPF).
     * Cannot be null.
     */
    @Column(name = "seller_ssn", nullable = false)
    private String sellerSsn;

    /**
     * Seller's email address.
     * Cannot be null.
     */
    @Column(name = "seller_email", nullable = false)
    private String sellerEmail;

    /**
     * Commission rate applied to the seller's sales.
     * Optional; defaults to 0.04 if not provided.
     */
    @Column(name = "seller_comrate")
    private Double sellerComRate;

    /**
     * Indicates whether the seller is currently active.
     * Optional; defaults to {@code true} if not provided.
     */
    private Boolean active;

    /**
     * List of sales associated with this seller.
     * Cascade operations ensure sales are persisted and removed with the seller.
     */
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesModel> sales = new ArrayList<>();

    /**
     * Constructs a {@code SellersModel} instance from a {@link RequestSellersDto}.
     *
     * @param dto DTO containing seller registration data.
     */
    public SellersModel(RequestSellersDto dto){
        this.sellerName = dto.sellerName();
        this.sellerSsn = dto.sellerSsn();
        this.sellerEmail = dto.sellerEmail();
        this.sellerComRate = dto.sellerComRate() != null ? dto.sellerComRate() : 0.04;
        this.active = dto.active() != null ? dto.active() : true;
    }
}
