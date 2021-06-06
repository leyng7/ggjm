package me.ryeong.ggjm.domain;

import lombok.Getter;

import javax.persistence.*;

@Table(name = "TB_PARTY_MEMBER")
@Entity
@Getter
public class PartyMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
