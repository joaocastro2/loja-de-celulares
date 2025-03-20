package com.loja.loja_de_celulares.Estoque.controller;


import com.loja.loja_de_celulares.Estoque.DTO.RequestEstoqueDTO;
import com.loja.loja_de_celulares.Estoque.model.Estoque;
import com.loja.loja_de_celulares.Estoque.repository.EstoqueRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    public ResponseEntity cadastroEstoque(@RequestBody @Valid RequestEstoqueDTO novoProduto){
        Estoque estoque = new Estoque(novoProduto);
        estoqueRepository.save(estoque);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity consultaEStoque(){
        var consultaEstoque = estoqueRepository.findAllByAtivoTrue();
        return ResponseEntity.ok(consultaEstoque);
    }


}
