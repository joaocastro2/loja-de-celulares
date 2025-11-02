package br.com.phone.store.tables.suppliers.repository;

import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/* Inherits CRUD operations via JpaRepository and allows creation of custom queries.
Use it to handle business logic related to supplier retrieval and persistence. */
public interface SuppliersRepository extends JpaRepository<SuppliersModel, UUID> {



}
