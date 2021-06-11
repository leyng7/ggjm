package me.ryeong.ggjm.domain;

import lombok.Getter;
import lombok.Setter;
import me.ryeong.ggjm.dto.PartyDTO;
import me.ryeong.ggjm.dto.RestaurantDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "TB_PARTY")
@Entity
@Getter @Setter
public class Party {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    private String name;

    // 상태값

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "party")
    private List<PartyMember> partyMembers = new ArrayList<>();


    public PartyDTO toPartyDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setLet(restaurant.getLet());
        restaurantDTO.setLng(restaurant.getLng());
        restaurantDTO.setType(restaurant.getType().getDisplayValue());

        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setId(id);
        partyDTO.setName(name);
        partyDTO.setRestaurant(restaurantDTO);
        partyDTO.setPartyMembersSize(partyMembers.size() + 1);

        return partyDTO;
    }
}
