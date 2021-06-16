package me.ryeong.ggjm.service;

import lombok.RequiredArgsConstructor;
import me.ryeong.ggjm.domain.Restaurant;
import me.ryeong.ggjm.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void update(Restaurant restaurant) {
        Restaurant findRestaurant = restaurantRepository.findById(restaurant.getId()).orElseThrow();

        findRestaurant.setName(restaurant.getName());
        findRestaurant.setLet(restaurant.getLet());
        findRestaurant.setLng(restaurant.getLng());
        findRestaurant.setType(restaurant.getType());
        findRestaurant.setArea(restaurant.getArea());
    }

}
