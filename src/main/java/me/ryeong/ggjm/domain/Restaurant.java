package me.ryeong.ggjm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "TB_RESTAURANT")
@Entity
@Getter @Setter
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    private String kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
