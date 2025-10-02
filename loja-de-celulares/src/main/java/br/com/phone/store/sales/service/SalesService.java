package br.com.phone.store.sales.service;

import br.com.phone.store.customers.repository.CustomersRepository;
import br.com.phone.store.sale_items.dto.RequestSaleItemsDto;
import br.com.phone.store.sale_items.model.SaleItemsModel;
import br.com.phone.store.sales.dto.RequestSalesDto;
import br.com.phone.store.sales.model.SalesModel;
import br.com.phone.store.sales.repository.SalesRepository;
import br.com.phone.store.sellers.repository.SellersRepository;
import br.com.phone.store.stock.model.StockModel;
import br.com.phone.store.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final CustomersRepository customersRepository;
    private final SellersRepository sellersRepository;
    private final StockRepository stockRepository;

    public SalesService(SalesRepository salesRepository,
                        CustomersRepository customersRepository,
                        SellersRepository sellersRepository,
                        StockRepository stockRepository) {
        this.salesRepository = salesRepository;
        this.customersRepository = customersRepository;
        this.sellersRepository = sellersRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public SalesModel createSale(RequestSalesDto dto) {
        var customer = customersRepository.findById(dto.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        var seller = sellersRepository.findById(dto.sellerId())
                .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));

        SalesModel sale = new SalesModel(customer, seller);

        BigDecimal total = BigDecimal.ZERO;
        List<SaleItemsModel> items = new ArrayList<>();

        for (RequestSaleItemsDto itemDto : dto.items()) {
            StockModel product = stockRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            if (product.getAmount() < itemDto.saleItemsQtty()) {
                throw new IllegalArgumentException("Estoque insuficiente para produto " + product.getProductName());
            }

            // Atualiza o estoque
            product.setAmount(product.getAmount() - itemDto.saleItemsQtty());
            stockRepository.save(product);

            // Calcula preço unitário (em reais)
            BigDecimal unitPrice = BigDecimal.valueOf(product.getPrice_in_cents())
                    .divide(BigDecimal.valueOf(100));
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDto.saleItemsQtty()));

            total = total.add(subtotal);

            // Cria item da venda
            SaleItemsModel saleItem = new SaleItemsModel(itemDto, sale, product, unitPrice.doubleValue());
            saleItem.setUnitPrice(unitPrice.doubleValue());
            saleItem.setSaleItemsSubtotal(subtotal.doubleValue());

            items.add(saleItem);
        }

        sale.setItems(items);
        sale.setTotalAmount(total.doubleValue());

        return salesRepository.save(sale);
    }

    public SalesModel getSale(Integer id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    }
}
