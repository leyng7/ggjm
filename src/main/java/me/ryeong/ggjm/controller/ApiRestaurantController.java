package me.ryeong.ggjm.controller;

import lombok.RequiredArgsConstructor;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.domain.Restaurant;
import me.ryeong.ggjm.dto.PartyDTO;
import me.ryeong.ggjm.dto.RestaurantDTO;
import me.ryeong.ggjm.repository.PartyRepository;
import me.ryeong.ggjm.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ApiRestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final PartyRepository partyRepository;

    @GetMapping("/api/restaurants")
    public List<RestaurantDTO> list() {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map(Restaurant::toRestaurantDTO).collect(Collectors.toList());
    }

    @GetMapping("/api/restaurants/{id}/parties")
    public List<PartyDTO> view(@PathVariable Long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        List<Party> parties = partyRepository.findAllByRestaurantWithMembers(restaurant);

        return parties.stream().map(Party::toPartyDTO).collect(Collectors.toList());
    }


}
