package com.delivery.controller;

import com.delivery.model.DTO.PlaceDTO;
import com.delivery.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

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
        model.addAttribute("foods", service.findAllFoodsByPlaceId(placeId));
        return "place_item";
    }

    @GetMapping("/admin/places/add")
    public String viewAddPlace() {
        return "create_place";
    }

    @PostMapping("/admin/places/add")
    public String addPlace(@Valid PlaceDTO placeDTO) {
        service.addPlace(placeDTO);
        return "redirect:/";
    }

    @PostMapping("/admin/places/delete")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deletePlace(@RequestParam int placeId, RedirectAttributes redirectAttributes)  {
        if (!service.deletePlace(placeId)) {
            redirectAttributes.addFlashAttribute("psqlError", "В данном заведений еще есть блюда. Удалите все его блюда чтобы удалить заведение");
        }
        return "redirect:/";
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected String handleBind(BindException ex, Model model) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.toList());
        model.addAttribute("bindErrors", errors);
        return "create_place";
    }

}
