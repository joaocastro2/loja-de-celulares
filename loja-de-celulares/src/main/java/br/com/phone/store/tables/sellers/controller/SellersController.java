package br.com.phone.store.tables.sellers.controller;

import br.com.phone.store.tables.sellers.dto.RequestSellersDto;
import br.com.phone.store.tables.sellers.model.SellersModel;
import br.com.phone.store.tables.sellers.repository.SellersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellersController {

    @Autowired
    private SellersRepository sellersRepository;

    @PostMapping
    public ResponseEntity<SellersModel> registerSeller(@RequestBody @Valid RequestSellersDto newSeller) {
        SellersModel sellersModel = new SellersModel(newSeller);
        SellersModel saved = sellersRepository.save(sellersModel);
        return ResponseEntity.ok(saved);
    }

}
