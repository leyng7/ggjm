package me.ryeong.ggjm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantDTO {

    private Long id;
    private String name;
    private Double let;
    private Double lng;
    private String type;
    private String area;

}
