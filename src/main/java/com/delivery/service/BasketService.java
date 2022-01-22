package com.delivery.service;

import com.delivery.model.*;
import com.delivery.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public void addFoodToBasket(int foodId, String email) {
        Food food = foodRepository.getById(foodId);
        Basket basket = basketRepository.findByUserEmailAndPlaceId(email, food.getPlace().getId());

        if (basket != null) {
            List<BasketItem> basketItems = basket.getBasketItems();

            Optional<BasketItem> basketItemOptional = basketItems
                    .stream()
                    .filter(i -> i.getFood().getId() == foodId)
                    .findFirst();

            BasketItem basketItem;
            if (!basketItemOptional.isEmpty()) {
                basketItem = basketItemOptional.get();
                basketItem.setCount(basketItem.getCount() + 1);
            } else {
                basketItem = getNewBasketItem(food, basket);
                basket.getBasketItems().add(basketItem);
            }
            basketItemRepository.save(basketItem);
            basket.setSum(basket.getBasketItems()
                    .stream()
                    .map(i -> i.getCount() * i.getFood().getPrice())
                    .collect(Collectors.summingDouble(Double::doubleValue)));
            basketRepository.save(basket);
        } else {
            basket = new Basket();
            User user = userRepository.findByEmail(email);
            basket.setUser(user);
            basket.setPlace(food.getPlace());
            basket.setSum(food.getPrice());
            basketRepository.save(basket);
            BasketItem basketItem = getNewBasketItem(food, basket);
            basketItemRepository.save(basketItem);
            basket.getBasketItems().add(basketItem);
            basketRepository.save(basket);
        }
    }

    public BasketItem getNewBasketItem(Food food, Basket basket) {
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setFood(food);
        basketItem.setCount(1);
        return basketItem;
    }

    public Basket getBasketByEmailAndFoodId(String email, int foodId) {
        Food food = foodRepository.getById(foodId);
        return basketRepository.findByUserEmailAndPlaceId(email, food.getPlace().getId());
    }

    public Basket getBasketByEmailAndPlaceId(String email, int placeId) {
        return basketRepository.findByUserEmailAndPlaceId(email, placeId);
    }

    public void deleteFoodFromBasket(int basketItemId) {
        BasketItem basketItem = basketItemRepository.getById(basketItemId);
        Basket basket = basketRepository.getById(basketItem.getBasket().getId());
        int count = basketItem.getCount();
        if (count > 1) {
            basketItem.setCount(count - 1);
            basketItemRepository.save(basketItem);
        } else {
            basketItemRepository.delete(basketItem);
        }
        basket.setSum(basket.getBasketItems()
                .stream()
                .map(i -> i.getCount() * i.getFood().getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue)));
        basketRepository.save(basket);
    }
}
