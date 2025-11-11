package br.com.phone.store.tables.stock.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) used to receive product stock information from the user.
 *
 * <p>This record encapsulates the necessary details for registering or updating a product
 * in the stock system, including its name, price, quantity, supplier, and active status.</p>
 *
 * <p>Validation annotations ensure that required fields are properly filled before processing.</p>
 *
 * @param product_id Unique identifier of the product (optional).
 * @param product_name Name of the product. Must not be blank.
 * @param price_in_cents Product price expressed in cents. Must not be null.
 * @param amount Quantity of the product available in stock (optional).
 * @param supplier_id UUID of the supplier providing the product. Must not be blank.
 * @param active Indicates whether the product is currently active (optional).
 */
public record RequestStockDto(

        UUID product_id,

        @NotBlank(message = "O nome do produto não pode estar vazio.")
        String product_name,

        @NotNull(message = "O preço em centavos é obrigatório.")
        Integer price_in_cents,

        Integer amount,

        @NotBlank(message = "O ID do fornecedor é obrigatório.")
        String supplier_id,

        Boolean active
) {}
