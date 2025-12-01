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
 * (Comentários mantidos e aprimorados)
 */
@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final CustomersRepository customersRepository;
    private final SellersRepository sellersRepository;
    private final StockRepository stockRepository;

    // --- EXCEÇÕES PERSONALIZADAS ---

    // Usada para retornar 404 NOT FOUND (Crie esta classe em um pacote de utilitários)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    // Usada para retornar 400 BAD REQUEST/409 CONFLICT (Problema de estoque/negócio)
    public static class BusinessConstraintException extends RuntimeException {
        public BusinessConstraintException(String message) {
            super(message);
        }
    }
    // ---------------------------------

    /**
     * Constructs a {@code SalesService} with the required repositories.
     */
    public SalesService(SalesRepository salesRepository,
                        CustomersRepository customersRepository,
                        SellersRepository sellersRepository,
                        StockRepository stockRepository) {
        // Uso de injeção por construtor (prática recomendada)
        this.salesRepository = salesRepository;
        this.customersRepository = customersRepository;
        this.sellersRepository = sellersRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Creates a new sale transaction.
     *
     * @param dto DTO containing sale details and items.
     * @return The persisted {@link SalesModel} representing the completed sale.
     * @throws ResourceNotFoundException se cliente, vendedor ou produto não for encontrado (404).
     * @throws BusinessConstraintException se o estoque for insuficiente (400/409).
     */
    @Transactional
    public SalesModel createSale(RequestSalesDto dto) {
        // Validação: usa ResourceNotFoundException para indicar 404
        var customer = customersRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + dto.customerId() + " não encontrado."));
        var seller = sellersRepository.findById(dto.sellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor com ID " + dto.sellerId() + " não encontrado."));

        SalesModel sale = new SalesModel(customer, seller);

        BigDecimal total = BigDecimal.ZERO;
        List<SaleItemsModel> items = new ArrayList<>();

        for (RequestSaleItemsDto itemDto : dto.items()) {
            // Validação de Produto
            StockModel product = stockRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + itemDto.productId() + " não encontrado."));

            // Verificação de Estoque
            if (product.getAmount() < itemDto.saleItemsQtty()) {
                throw new BusinessConstraintException("Estoque insuficiente. Produto " + product.getProductName() + " tem apenas " + product.getAmount() + " unidades disponíveis.");
            }

            // Atualização de Estoque
            product.setAmount(product.getAmount() - itemDto.saleItemsQtty());
            stockRepository.save(product);

            // Cálculo do Preço
            // Melhorando a manipulação de centavos para BigDecimal
            BigDecimal unitPrice = BigDecimal.valueOf(product.getPrice_in_cents()).movePointLeft(2);
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDto.saleItemsQtty()));

            total = total.add(subtotal);

            // Criação do Item de Venda
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
     * @throws ResourceNotFoundException if the sale is not found.
     */
    public SalesModel getSale(Integer id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda com ID " + id + " não encontrada."));
    }
}