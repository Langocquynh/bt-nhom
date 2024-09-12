package com.deltadc.quizletclone.folder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("SELECT f FROM Folder f WHERE f.user.user_id = :userId")
    List<Folder> findByUserId(Long userId);


    Page<Folder> findByIsPublic(boolean aTrue, Pageable pageable);

    Page<Folder> findByTitleContainingAndIsPublic(String title, boolean b, Pageable pageable);
}
