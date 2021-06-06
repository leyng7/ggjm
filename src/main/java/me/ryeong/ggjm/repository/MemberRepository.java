package me.ryeong.ggjm.repository;

import me.ryeong.ggjm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
