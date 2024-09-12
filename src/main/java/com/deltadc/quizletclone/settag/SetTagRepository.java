package com.deltadc.quizletclone.settag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SetTagRepository extends JpaRepository<SetTag, Long> {

    @Query("SELECT st FROM SetTag st WHERE st.set_id = :setId")
    SetTag findBySetId(Long setId);
}
