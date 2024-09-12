package com.deltadc.quizletclone.folder;

import com.deltadc.quizletclone.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
@CrossOrigin(origins = "http://localhost:3000")
public class FolderController {
    private final FolderService folderService;

    private boolean isEmptyFolderInput(Folder folder) {
        return folder.getTitle().isEmpty() || folder.getDescription().isEmpty() || String.valueOf(folder.isPublic()).isEmpty();
    }

    private FolderDTO convertToDTO(Folder folder) {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setFolderId(folder.getFolder_id());
        folderDTO.setTitle(folder.getTitle());
        folderDTO.setDescription(folder.getDescription());
        folderDTO.setCreatedAt(folder.getCreatedAt());
        folderDTO.setUpdatedAt(folder.getUpdatedAt());
        folderDTO.setPublic(folder.isPublic());
        return folderDTO;
    }

    //tạo folder mới
    @PostMapping("/create-folder")
    public ResponseEntity<?> createFolder(@RequestBody Folder folder) {
        if(isEmptyFolderInput(folder)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Không được để trống");
        }

        return folderService.createFolder(folder);
    }

    //lay toan bo cac folder hien co
    @GetMapping("/get-all-folders")
    public ResponseEntity<?> getAllFolders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Page<Folder> folderPage = folderService.getAllFolders(page, size);

        return ResponseEntity.ok(folderPage);
    }

    //lay toan bo public folder
    @GetMapping("/get-public-folders")
    public ResponseEntity<?> getPublicFolders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Page<Folder> folderPage = folderService.getPublicFolders(page, size);

        return ResponseEntity.ok(folderPage);
    }

    //lấy toàn bộ folder của người dùng dựa trên user_id
    @GetMapping("/{userId}/folders")
    public ResponseEntity<?> getUserFolders(@PathVariable("userId") Long userId) {
        List<Folder> userFolders = folderService.getUserFolders(userId);
        List<FolderDTO> userFolderDTOs = userFolders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userFolderDTOs);
    }

    // xóa folder dựa trên folderId
    @DeleteMapping("/{folderId}")
    public ResponseEntity<String> deleteFolder(@PathVariable("folderId") Long folderId) {
        return folderService.deleteFolder(folderId);
    }

    //lấy tất cả các set trong một folder dựa trên folderId
    @GetMapping("/{folderId}/sets")
    public ResponseEntity<?> getSetsInFolder(@PathVariable("folderId") Long folderId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return folderService.getSetsInFolder(folderId, page, size);
    }

    //edit folder theo folderId
    @PutMapping("/edit/{folderId}")
    public ResponseEntity<?> editFolderById(@PathVariable("folderId") Long folderId, @RequestBody Folder newFolder) {
        if(isEmptyFolderInput(newFolder)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Không được để trống");
        }

        return folderService.editFolderById(folderId, newFolder);
    }

    //tim folder theo title
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getFolderByTitle(@PathVariable("title") String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Page<Folder> folderPage = folderService.getFolderByTitle(title, page, size);

        return ResponseEntity.ok(folderPage);
    }
}
