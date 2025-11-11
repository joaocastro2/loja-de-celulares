package br.com.phone.store.tables.sale_items.dto;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) used to receive sale item details during a sales transaction.
 *
 * <p>This record encapsulates the necessary information for associating a product with a sale,
 * including the product identifier, quantity sold, and optionally the unit price.</p>
 *
 * <p>The unit price can be set manually or calculated automatically in the service layer.</p>
 *
 * @param productId Unique identifier of the product being sold.
 * @param saleItemsQtty Quantity of the product included in the sale.
 * @param saleItemsUnitPrice Unit price of the product at the time of sale (optional).
 */
public record RequestSaleItemsDto(
        UUID productId,
        Integer saleItemsQtty,
        Double saleItemsUnitPrice
) {}
