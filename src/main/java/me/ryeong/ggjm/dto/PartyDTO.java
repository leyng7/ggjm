package me.ryeong.ggjm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartyDTO {

    private Long id;
    private String name;
    private RestaurantDTO restaurant;
    private int partyMembersSize;

}
