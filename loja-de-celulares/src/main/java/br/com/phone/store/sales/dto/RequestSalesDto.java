package br.com.phone.store.sales.dto;

import br.com.phone.store.sale_items.model.dto.RequestSaleItemsDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RequestSalesDto(

        Integer customerId,
        Integer sellerId,
        @NotNull List<RequestSaleItemsDto> items
){}
