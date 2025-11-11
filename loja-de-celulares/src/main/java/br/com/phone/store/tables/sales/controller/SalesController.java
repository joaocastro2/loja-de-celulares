package br.com.phone.store.tables.sales.controller;

import br.com.phone.store.tables.sales.dto.RequestSalesDto;
import br.com.phone.store.tables.sales.model.SalesModel;
import br.com.phone.store.tables.sales.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for handling sales-related operations.
 *
 * <p>This controller provides endpoints for creating and retrieving sales records
 * in the phone store system.</p>
 *
 * <p>It delegates business logic to the {@link SalesService} and exposes HTTP endpoints
 * under the {@code /sales} path.</p>
 */
@RestController
@RequestMapping("/sales")
public class SalesController {

    /**
     * Service layer responsible for processing sales logic.
     */
    private final SalesService salesService;

    /**
     * Constructs a {@code SalesController} with the required {@link SalesService}.
     *
     * @param salesService Service used to handle sales operations.
     */
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    /**
     * Creates a new sale.
     *
     * <p>This endpoint receives a {@link RequestSalesDto} containing sale details,
     * processes the sale via the service layer, and returns the saved {@link SalesModel}.</p>
     *
     * @param dto DTO containing sale information.
     * @return ResponseEntity with the created {@link SalesModel}.
     */
    @PostMapping
    public ResponseEntity<SalesModel> createSale(@RequestBody RequestSalesDto dto) {
        SalesModel sale = salesService.createSale(dto);
        return ResponseEntity.ok(sale);
    }

    /**
     * Retrieves a sale by its ID.
     *
     * <p>This endpoint fetches a {@link SalesModel} based on the provided sale ID.</p>
     *
     * @param id Unique identifier of the sale.
     * @return ResponseEntity with the retrieved {@link SalesModel}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SalesModel> getSale(@PathVariable Integer id) {
        SalesModel sale = salesService.getSale(id);
        return ResponseEntity.ok(sale);
    }
}
