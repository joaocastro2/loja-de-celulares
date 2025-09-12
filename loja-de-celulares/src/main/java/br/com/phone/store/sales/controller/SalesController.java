package br.com.phone.store.sales.controller;

import br.com.phone.store.sales.dto.RequestSalesDto;
import br.com.phone.store.sales.model.SalesModel;
import br.com.phone.store.sales.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<SalesModel> createSale(@RequestBody RequestSalesDto dto) {
        SalesModel sale = salesService.createSale(dto);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesModel> getSale(@PathVariable Integer id) {
        SalesModel sale = salesService.getSale(id);
        return ResponseEntity.ok(sale);
    }
}
