package me.ryeong.ggjm.repository;

import me.ryeong.ggjm.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
