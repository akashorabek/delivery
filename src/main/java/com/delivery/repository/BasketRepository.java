package com.delivery.repository;

import com.delivery.model.Basket;
import com.delivery.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Basket findByUserEmailAndPlaceId(String email, int placeId);
}
