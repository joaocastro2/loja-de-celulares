package br.com.phone.store.tables.sales.dto;

import br.com.phone.store.tables.sale_items.dto.RequestSaleItemsDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestSalesDto(

        Integer customerId,
        Integer sellerId,
        @NotNull List<RequestSaleItemsDto> items
){}
