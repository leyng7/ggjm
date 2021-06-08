package me.ryeong.ggjm.repository;

import me.ryeong.ggjm.domain.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query(value = "select p from Party p " +
            "left join fetch p.partyMembers " +
            "left join fetch p.restaurant ",
            countQuery = "select count(p) from Party p")
    Page<Party> findAllWithMembers(Pageable pageable);

    @Query(value = "select p from Party p " +
            "left join fetch p.member " +
            "where p.id = :id")
    Optional<Party> findByIdWithMember(Long id);

}
