package br.com.phone.store.tables.users.repository;

import br.com.phone.store.tables.users.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository <UsersModel, Integer> {

    Optional<UsersModel> findByEmail(String userEmail);

}
