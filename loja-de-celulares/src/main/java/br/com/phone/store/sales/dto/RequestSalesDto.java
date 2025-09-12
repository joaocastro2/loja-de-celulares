package br.com.phone.store.sales.dto;

import br.com.phone.store.sale_items.dto.RequestSaleItemsDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RequestSalesDto(

        Integer customerId,
        Integer sellerId,
        @NotNull List<RequestSaleItemsDto> items
){}
