package com.deltadc.quizletclone.card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.set.set_id = ?1")
    Page<Card> findCardsBySetId(Long set_id, Pageable pageable);

    @Query("SELECT c FROM Card c WHERE c.set.set_id = ?1")
    List<Card> findListCardsBySetId(Long set_id);
}
