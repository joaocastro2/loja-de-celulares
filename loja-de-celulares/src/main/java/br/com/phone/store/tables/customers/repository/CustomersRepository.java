package br.com.phone.store.tables.customers.repository;

import br.com.phone.store.tables.customers.model.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersModel, Integer> {}
