package me.ryeong.ggjm.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberAdapter extends User {

    private final Member member;

    public MemberAdapter(Member member) {
        super(member.getUsername(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + member.getRole())));
        this.member = member;
    }

}
