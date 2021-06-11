package me.ryeong.ggjm.domain;

import lombok.Getter;

@Getter
public enum RestaurantArea {

    JI_JOK("지족동"),
    BAN_SEOK("반석동");

    private final String displayValue;

    RestaurantArea(String displayValue) {
        this.displayValue = displayValue;
    }
}
