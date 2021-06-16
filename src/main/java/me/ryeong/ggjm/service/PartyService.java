package me.ryeong.ggjm.service;

import lombok.RequiredArgsConstructor;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.domain.PartyMember;
import me.ryeong.ggjm.repository.PartyMemberRepository;
import me.ryeong.ggjm.repository.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;

    public void enterParty(Long id, Member member) {

        Party party = partyRepository.findById(id).orElseThrow();
        List<PartyMember> partyMembers = partyMemberRepository.findByPartyWithMember(party);
        Optional<PartyMember> existMember = partyMembers.stream().filter(partyMember -> partyMember.getMember().getId().equals(member.getId())).findFirst();
        if (existMember.isPresent()) throw new IllegalStateException("이미 가입을 한 파티 입니다.");
        if (partyMembers.size() > 3) throw new IllegalStateException("가입 멤버가 초과 하였습니다.");

        PartyMember partyMember = new PartyMember();
        partyMember.setParty(party);
        partyMember.setMember(member);

        partyMemberRepository.save(partyMember);
    }

    public void leaveParty(Long id, Member member) {
        Party party = partyRepository.findById(id).orElseThrow();
        partyMemberRepository.deleteByPartyAndMember(party, member);
    }
}
