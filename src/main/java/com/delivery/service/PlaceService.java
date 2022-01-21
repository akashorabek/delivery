package com.delivery.service;

import com.delivery.model.DTO.PlaceDTO;
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

    public void addPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setName(placeDTO.getName());
        place.setImage(placeDTO.getImage());
        place.setDescription(placeDTO.getDescription());

        repository.save(place);
    }
}
