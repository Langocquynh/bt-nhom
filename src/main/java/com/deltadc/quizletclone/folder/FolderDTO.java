package com.deltadc.quizletclone.folder;

import lombok.Getter;
import lombok.Setter;

public class FolderDTO {

    @Getter @Setter
    private Long folderId;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String createdAt;
    @Getter @Setter
    private String updatedAt;
    @Getter @Setter
    private boolean isPublic;
}
