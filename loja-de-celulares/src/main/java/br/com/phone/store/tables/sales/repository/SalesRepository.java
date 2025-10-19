package br.com.phone.store.tables.sales.repository;

import br.com.phone.store.tables.sales.model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository <SalesModel, Integer>{}
