package br.com.phone.store.sales.controller;

import br.com.phone.store.sales.dto.RequestSalesDto;
import br.com.phone.store.sales.model.SalesModel;
import br.com.phone.store.sales.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//SALES CLASS "SETTINGS"
@RestController
@RequestMapping("/sales")
public class SalesController {

    //DECLARE AN ATTRIBUTE WITHIN THE CLASS
    private final SalesService salesService;

    //CONSTRUCTOR METHOD
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }


    //MAKE A SALE
    @PostMapping
    public ResponseEntity<SalesModel> createSale(@RequestBody RequestSalesDto dto) {
        SalesModel sale = salesService.createSale(dto);
        return ResponseEntity.ok(sale);
    }

    //GET SALES
    @GetMapping("/{id}")
    public ResponseEntity<SalesModel> getSale(@PathVariable Integer id) {
        SalesModel sale = salesService.getSale(id);
        return ResponseEntity.ok(sale);
    }
}
