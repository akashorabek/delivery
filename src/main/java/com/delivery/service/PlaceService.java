package com.delivery.service;

import com.delivery.model.Place;
import com.delivery.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;

    public Iterable<Place> findAll() {
        return repository.findAll();
    }

    public Place getById(int placeId) {
        return repository.getById(placeId);
    }
}
