package me.ryeong.ggjm.domain;

import lombok.Getter;

@Getter
public enum RestaurantType {

    KOREAN_FOOD("한식"),
    CHINESE_FOOD("중식"),
    WESTERN_FOOD("양식"),
    JAPANESE_FOOD("일식");


    private final String displayValue;

    RestaurantType(String displayValue) {
        this.displayValue = displayValue;
    }
}
