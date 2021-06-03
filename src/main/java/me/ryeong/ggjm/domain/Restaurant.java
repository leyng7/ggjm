package me.ryeong.ggjm.domain;

import javax.persistence.*;

@Table(name = "TB_RESTAURANT")
@Entity
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    private String kind;

}
