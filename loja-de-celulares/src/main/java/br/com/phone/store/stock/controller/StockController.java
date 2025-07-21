package br.com.phone.store.stock.controller;


import br.com.phone.store.stock.dto.RequestStockDto;
import br.com.phone.store.stock.model.StockModel;
import br.com.phone.store.stock.repository.StockRepository;
import br.com.phone.store.suppliers.model.SuppliersModel;
import br.com.phone.store.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private SuppliersRepository supplierRepository;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid RequestStockDto newProduct) {
        try {
            // Valida o UUID do fornecedor
            UUID supplierUUID = UUID.fromString(newProduct.supplier_id());

            // Busca o fornecedor no banco
            SuppliersModel supplier = supplierRepository.findBySupplierId(supplierUUID)
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

            // Cria o produto com o fornecedor
            StockModel stockModel = new StockModel(newProduct, supplier);
            stockRepository.save(stockModel);

            return ResponseEntity.ok(stockModel);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("supplier_id inválido ou malformado.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<List<StockModel>>findById(@PathVariable String productId) {

        List<StockModel> productById = stockRepository.findByProductId(productId);
        Set<StockModel> finalResult = new LinkedHashSet<>();
        finalResult.addAll(productById);

        if (finalResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ArrayList<>(finalResult));
    }

    @GetMapping
    public ResponseEntity productQuery(){
        var stockQuery = stockRepository.findAllByActiveTrue();
        return ResponseEntity.ok(stockQuery);
    }

    @GetMapping("/{product_name}")
    public ResponseEntity<List<StockModel>> findByProductName(@PathVariable String product_name) {
        // Find by product name
        List<StockModel> exactProduct = stockRepository.findByProductNameIgnoreCase(product_name);

        // Search for similar names
        List<StockModel> similarProduct = stockRepository.findByProductNameContainingIgnoreCase(product_name);

        // Avoid duplicates
        Set<StockModel> finalResult = new LinkedHashSet<>();
        finalResult.addAll(exactProduct);
        finalResult.addAll(similarProduct);

        if (finalResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ArrayList<>(finalResult));
    }
}
