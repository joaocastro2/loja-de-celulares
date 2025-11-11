package br.com.phone.store.tables.sales.dto;

import br.com.phone.store.tables.sale_items.dto.RequestSaleItemsDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Data Transfer Object (DTO) used to receive sales transaction details.
 *
 * <p>This record encapsulates the necessary information to create a new sale,
 * including the customer, seller, and the list of items being sold.</p>
 *
 * <p>The {@code items} field is mandatory and must contain at least one {@link RequestSaleItemsDto}
 * representing the products included in the sale.</p>
 *
 * @param customerId Identifier of the customer making the purchase.
 * @param sellerId Identifier of the seller handling the transaction.
 * @param items List of sale items included in the transaction. Must not be null.
 */
public record RequestSalesDto(

        Integer customerId,
        Integer sellerId,
        @NotNull List<RequestSaleItemsDto> items
) {}
