package com.deltadc.quizletclone.folderset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderSetRepository extends JpaRepository<FolderSet, Long> {
    @Query("SELECT fs FROM FolderSet fs WHERE fs.folder_id = :folderId")
    List<FolderSet> findByFolderId(Long folderId);

    @Query("SELECT fs FROM FolderSet fs WHERE fs.set_id = :setId")
    List<FolderSet> findBySetId(Long setId);

    @Query("SELECT fs FROM FolderSet fs WHERE fs.folder_id = :folderId AND fs.set_id = :setId")
    Optional<FolderSet> findByFolderIdAndSetId(Long folderId, Long setId);

    @Query("SELECT fs FROM FolderSet fs WHERE fs.folder_id = :folderId")
    Page<FolderSet> findByFolderIdWithPageable(Long folderId, Pageable pageable);
}
