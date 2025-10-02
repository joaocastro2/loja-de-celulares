package br.com.phone.store.sale_items.model;

import br.com.phone.store.sale_items.dto.RequestSaleItemsDto;
import br.com.phone.store.sales.model.SalesModel;
import br.com.phone.store.stock.model.StockModel;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "sale_items")
@Entity(name = "sale_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "saleitems_id")
public class SaleItemsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saleitems_seq")
    @SequenceGenerator(name = "saleitems_seq", sequenceName = "saleitems_seq", allocationSize = 1)
    @Column(name = "saleitems_id")
    private Integer saleItemsId;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SalesModel sales;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private StockModel productId;

    @Column(name = "saleitems_qtty")
    private Integer saleItemsQtty;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "saleitems_subtotal", nullable = false)
    private Double saleItemsSubtotal;

    public SaleItemsModel(RequestSaleItemsDto dto, SalesModel sales, StockModel product, Double unitPrice) {
        this.sales = sales;
        this.productId = product;
        this.saleItemsQtty = dto.saleItemsQtty();
        this.unitPrice = unitPrice;
        this.saleItemsSubtotal = unitPrice * this.saleItemsQtty;
    }


}
