package com.dolbik.MobileOperator.repositories;

import com.dolbik.MobileOperator.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
}
