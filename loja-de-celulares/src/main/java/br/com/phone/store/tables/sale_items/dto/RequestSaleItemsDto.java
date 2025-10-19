package br.com.phone.store.tables.sale_items.dto;

import java.util.UUID;

public record RequestSaleItemsDto(
        UUID productId,
        Integer saleItemsQtty,
        Double saleItemsUnitPrice   // opcional (pode preencher no service)
) {}
