package br.com.phone.store.tables.sales.service;

import br.com.phone.store.tables.customers.repository.CustomersRepository;
import br.com.phone.store.tables.sale_items.dto.RequestSaleItemsDto;
import br.com.phone.store.tables.sale_items.model.SaleItemsModel;
import br.com.phone.store.tables.sales.dto.RequestSalesDto;
import br.com.phone.store.tables.sales.model.SalesModel;
import br.com.phone.store.tables.sales.repository.SalesRepository;
import br.com.phone.store.tables.sellers.repository.SellersRepository;
import br.com.phone.store.tables.stock.model.StockModel;
import br.com.phone.store.tables.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling business logic related to sales transactions.
 *
 * <p>This class coordinates the creation and retrieval of sales, including validation of
 * customer and seller data, stock updates, item pricing, and total calculation.</p>
 *
 * <p>It interacts with multiple repositories to persist and manage sales-related entities.</p>
 */
@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final CustomersRepository customersRepository;
    private final SellersRepository sellersRepository;
    private final StockRepository stockRepository;

    /**
     * Constructs a {@code SalesService} with the required repositories.
     *
     * @param salesRepository Repository for persisting sales.
     * @param customersRepository Repository for retrieving customer data.
     * @param sellersRepository Repository for retrieving seller data.
     * @param stockRepository Repository for managing product stock.
     */
    public SalesService(SalesRepository salesRepository,
                        CustomersRepository customersRepository,
                        SellersRepository sellersRepository,
                        StockRepository stockRepository) {
        this.salesRepository = salesRepository;
        this.customersRepository = customersRepository;
        this.sellersRepository = sellersRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Creates a new sale transaction.
     *
     * <p>This method validates customer and seller IDs, checks product availability,
     * updates stock quantities, calculates item subtotals and total amount, and persists
     * the sale and its items.</p>
     *
     * @param dto DTO containing sale details and items.
     * @return The persisted {@link SalesModel} representing the completed sale.
     * @throws IllegalArgumentException if customer, seller, or product is not found,
     *                                  or if stock is insufficient.
     */
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

            product.setAmount(product.getAmount() - itemDto.saleItemsQtty());
            stockRepository.save(product);

            BigDecimal unitPrice = BigDecimal.valueOf(product.getPrice_in_cents())
                    .divide(BigDecimal.valueOf(100));
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDto.saleItemsQtty()));

            total = total.add(subtotal);

            SaleItemsModel saleItem = new SaleItemsModel(itemDto, sale, product, unitPrice.doubleValue());
            saleItem.setUnitPrice(unitPrice.doubleValue());
            saleItem.setSaleItemsSubtotal(subtotal.doubleValue());

            items.add(saleItem);
        }

        sale.setItems(items);
        sale.setTotalAmount(total.doubleValue());

        return salesRepository.save(sale);
    }

    /**
     * Retrieves a sale by its ID.
     *
     * @param id Unique identifier of the sale.
     * @return The {@link SalesModel} corresponding to the given ID.
     * @throws IllegalArgumentException if the sale is not found.
     */
    public SalesModel getSale(Integer id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    }
}
