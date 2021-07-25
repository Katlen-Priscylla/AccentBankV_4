package com.accenture.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.bank.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
