package me.ryeong.ggjm.repository;

import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.domain.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {

    @Query(value = "select pm from PartyMember pm left join fetch pm.member where pm.party = :party")
    List<PartyMember> findByPartyWithMember(Party party);

    void deleteByPartyAndMember(Party party, Member member);

}
