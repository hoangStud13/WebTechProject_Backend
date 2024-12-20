package de.htwberlin.accountservice.repository;

import de.htwberlin.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional <Account> findByEmailAndPassword(String email, String password);
}
