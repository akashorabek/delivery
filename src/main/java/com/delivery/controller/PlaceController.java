package com.delivery.controller;

import com.delivery.repository.PlaceRepository;
import com.delivery.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class PlaceController {
    private final PlaceService service;

    @GetMapping
    public String root(Model model) {
        model.addAttribute("places", service.findAll());
        return "index";
    }

    @GetMapping("/places/{placeId}")
    public String viewPlaceItem(@PathVariable int placeId, Model model) {
        model.addAttribute("place", service.getById(placeId));
        return "place_item";
    }
}
