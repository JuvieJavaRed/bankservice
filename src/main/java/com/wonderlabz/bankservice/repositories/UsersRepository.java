package com.wonderlabz.bankservice.repositories;

import com.wonderlabz.bankservice.entities.Transactions;
import com.wonderlabz.bankservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("FROM Users WHERE email = ?1")
    Users getByEmail(String email);
}
