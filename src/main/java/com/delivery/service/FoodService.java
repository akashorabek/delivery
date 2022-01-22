package com.delivery.service;

import com.delivery.model.DTO.FoodDTO;
import com.delivery.model.Food;
import com.delivery.repository.FoodRepository;
import com.delivery.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository repository;
    private final PlaceRepository placeRepository;

    public void addFood(FoodDTO foodDTO) {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setPrice(foodDTO.getPrice());
        food.setDescription(foodDTO.getDescription());
        food.setPlace(placeRepository.getById(foodDTO.getPlaceId()));
        repository.save(food);
    }

    public int getFoodsPlaceId(int foodId) {
        Food food = repository.getById(foodId);
        return food.getPlace().getId();
    }

    public void deleteFood(int foodId) {
        repository.deleteById(foodId);
    }
}
