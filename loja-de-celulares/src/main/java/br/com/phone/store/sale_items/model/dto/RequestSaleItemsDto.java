package br.com.phone.store.sale_items.model.dto;

public record RequestSaleItemsDto(
        Long productId,
        Integer saleItemsQtty,
        Double saleItemsUnitPrice   // opcional (pode preencher no service)
) {}
