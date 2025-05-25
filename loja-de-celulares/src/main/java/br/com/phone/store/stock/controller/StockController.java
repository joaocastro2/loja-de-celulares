package br.com.phone.store.stock.controller;


import br.com.phone.store.stock.dto.RequestStockDto;
import br.com.phone.store.stock.model.StockModel;
import br.com.phone.store.stock.repository.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @PostMapping
    public ResponseEntity registerStock(@RequestBody @Valid RequestStockDto newProduct){
        StockModel stockModel = new StockModel(newProduct);
        stockRepository.save(stockModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity stockQuery(){
        var stockQuery = stockRepository.findAllByActiveTrue();
        return ResponseEntity.ok(stockQuery);
    }


}
