package com.delivery.repository;

import com.delivery.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {
    List<BasketItem> findAllByBasketId(int basketId);
}
