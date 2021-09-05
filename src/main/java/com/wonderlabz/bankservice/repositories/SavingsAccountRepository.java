package com.wonderlabz.bankservice.repositories;

import com.wonderlabz.bankservice.entities.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
