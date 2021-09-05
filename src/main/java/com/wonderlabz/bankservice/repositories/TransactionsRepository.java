package com.wonderlabz.bankservice.repositories;

import com.wonderlabz.bankservice.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByEmail(String email);
}
