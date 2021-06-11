package me.ryeong.ggjm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {

    private Long id;
    private String name;
    private String type;
    private Double let;
    private Double lng;

}
