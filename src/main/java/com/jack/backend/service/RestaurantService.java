package com.jack.backend.service;

import com.jack.backend.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    int save(Restaurant restaurant);

    Restaurant query(Long id);

    List<Restaurant> queryAll();
}
