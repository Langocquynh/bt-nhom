package com.deltadc.quizletclone.settag;

import com.deltadc.quizletclone.set.Set;
import com.deltadc.quizletclone.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "set_tags")
public class SetTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_tag_id")
    private Long set_tag_id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tag_id", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "set_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Set set;

    private Long set_id;
    private Long tag_id;
}
