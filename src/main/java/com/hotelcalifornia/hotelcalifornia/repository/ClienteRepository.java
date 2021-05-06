package com.hotelcalifornia.hotelcalifornia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelcalifornia.hotelcalifornia.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
