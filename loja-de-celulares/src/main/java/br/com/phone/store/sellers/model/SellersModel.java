package br.com.phone.store.sellers.model;


import br.com.phone.store.sales.model.SalesModel;
import br.com.phone.store.sellers.dto.RequestSellersDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name= "sellers")
@Entity(name= "sellers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sellerId")
public class SellersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_seq")
    @SequenceGenerator(name = "seller_seq", sequenceName = "seller_seq", allocationSize = 1)
    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name= "seller_name", nullable = false)
    private String sellerName;

    @Column(name= "seller_ssn", nullable = false)
    private String sellerSsn;

    @Column(name = "seller_email", nullable = false)
    private String sellerEmail;

    @Column(name = "seller_comrate")
    private Double sellerComRate;

    private Boolean active;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesModel> sales = new ArrayList<>();

    public SellersModel(RequestSellersDto dto){
        this.sellerName = dto.sellerName();
        this.sellerSsn = dto.sellerSsn();
        this.sellerEmail = dto.sellerEmail();
        this.sellerComRate = dto.sellerComRate() != null ? dto.sellerComRate() : 0.04;
        this.active = dto.active() != null ? dto.active() : true;
    }
}
