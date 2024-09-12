package com.deltadc.quizletclone.set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    @Query("SELECT s FROM Set s WHERE s.user_id = :userId")
    Page<Set> findByUserId(Long userId, Pageable pageable);

    Page<Set> findByIsPublic(boolean b, Pageable pageable);

    List<Set> findByTitleLike(String title);

    List<Set> findByTitleContaining(String title);

    Page<Set> findByTitleContainingAndIsPublic(String title, boolean b, Pageable pageable);
}
