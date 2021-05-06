package com.hotelcalifornia.hotelcalifornia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelcalifornia.hotelcalifornia.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
