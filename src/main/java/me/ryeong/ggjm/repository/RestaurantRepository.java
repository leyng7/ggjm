package me.ryeong.ggjm.repository;

import me.ryeong.ggjm.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
