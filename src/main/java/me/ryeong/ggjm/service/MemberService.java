package me.ryeong.ggjm.service;

import lombok.RequiredArgsConstructor;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.domain.MemberAdapter;
import me.ryeong.ggjm.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        Member member = findMember.orElseThrow(() -> new UsernameNotFoundException(username));

        return new MemberAdapter(member);
    }

    public Member join(Member member) {
        member.encodePassword(passwordEncoder);
        return memberRepository.save(member);
    }

}
