package com.delivery.controller;

import com.delivery.model.Basket;
import com.delivery.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/basket/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Basket addFoodToBasket(@RequestParam int foodId, Authentication authentication) {
        basketService.addFoodToBasket(foodId, authentication.getName());
        return basketService.getBasketByEmailAndFoodId(authentication.getName(), foodId);
    }

    @PostMapping("/basket/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public Basket deleteFoodFromBasket(@RequestParam int basketItemId, @RequestParam int placeId, Authentication authentication) {
        basketService.deleteFoodFromBasket(basketItemId);
        return basketService.getBasketByEmailAndPlaceId(authentication.getName(), placeId);
    }

    @GetMapping("/basket/{placeId}")
    public Basket getBasket(@PathVariable int placeId,Authentication authentication) {
        return basketService.getBasketByEmailAndPlaceId(authentication.getName(), placeId);
    }
}
