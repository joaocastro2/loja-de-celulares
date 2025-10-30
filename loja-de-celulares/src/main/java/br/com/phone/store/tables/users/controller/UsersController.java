package br.com.phone.store.tables.users.controller;

import org.hibernate.annotations.WhereJoinTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Sucesso");
    }

}