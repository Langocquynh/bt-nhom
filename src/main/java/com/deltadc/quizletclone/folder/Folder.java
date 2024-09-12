package com.deltadc.quizletclone.folder;

import com.deltadc.quizletclone.folderset.FolderSet;
import com.deltadc.quizletclone.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder")
public class Folder {

    public Folder(Long user_id, String title, String description, boolean isPublic) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long folder_id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    private String createdAt;

    private String updatedAt;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FolderSet> folderSets;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now().toString();
    }
}
