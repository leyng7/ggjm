package me.ryeong.ggjm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "TB_MEMBER")
@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private String nickname;

}
