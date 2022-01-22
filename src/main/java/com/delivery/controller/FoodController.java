package com.delivery.controller;

import com.delivery.model.DTO.FoodDTO;
import com.delivery.service.FoodService;
import com.delivery.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class FoodController {
    private final FoodService service;
    private final PlaceService placeService;

    @GetMapping("/admin/foods/add")
    public String viewFoodsAdd(Model model) {
        model.addAttribute("places", placeService.findAll());
        return "create_food";
    }

    @PostMapping("/admin/foods/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addFood(@Valid FoodDTO foodDTO) {
        service.addFood(foodDTO);
        return "redirect:/places/" + foodDTO.getPlaceId();
    }

    @PostMapping("/admin/foods/delete")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteFood(@RequestParam int foodId)  {
        int redirectingPlaceId = service.getFoodsPlaceId(foodId);
        service.deleteFood(foodId);
        return "redirect:/places/" + redirectingPlaceId;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.SEE_OTHER)
    protected String handleBind(BindException ex, RedirectAttributes attributes) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.toList());
        attributes.addFlashAttribute("bindErrors", errors);
        return "redirect:/admin/foods/add";
    }
}
