package me.ryeong.ggjm.domain;

import lombok.Getter;

import javax.persistence.*;

@Table(name = "TB_PARTY")
@Entity
@Getter
public class Party {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    private String name;

    // 상태값

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
