package com.deltadc.quizletclone.folderset;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder-set")
@CrossOrigin(origins = "http://localhost:3000")
public class FolderSetController {
    private final FolderSetService folderSetService;

    // tạo folder set mới
    @PostMapping("/create-folder-set")
    public ResponseEntity<?> createFolderSet(@RequestBody FolderSet folderSet) {
        return folderSetService.createFolderSet(folderSet);
    }

    // xóa folder set
    @DeleteMapping("/delete/{folderSetId}")
    public ResponseEntity<?> deleteFolderSetById(@PathVariable("folderSetId") Long folderSetId) {
        return folderSetService.deleteFolderSetById(folderSetId);
    }

    //lay folder set theo folderSetId
    @GetMapping("/{folderSetId}")
    public ResponseEntity<?> getFolderSetById(@PathVariable("folderSetId") Long folderSetId) {
        return folderSetService.getFolderSetById(folderSetId);
    }

    //lay tat ca cac folder set
    @GetMapping("/get-all-folder-sets")
    public ResponseEntity<?> getAllFolderSets() {
        return folderSetService.getAllFolderSets();
    }

    //lay tat ca cac folder set theo folderId
    @GetMapping("/folder/{folderId}")
    public ResponseEntity<?> getFolderSetByFolderId(@PathVariable("folderId") Long folderId) {
        return folderSetService.getFolderSetByFolderId(folderId);
    }

    //lay tat ca cac folder set theo setId
    @GetMapping("/set/{setId}")
    public ResponseEntity<?> getFolderSetBySetId(@PathVariable("setId") Long setId) {
        return folderSetService.getFolderSetBySetId(setId);
    }

    //sua folder set
    @PutMapping("/edit/{folderSetId}")
    public ResponseEntity<?> editFolderSetById(@PathVariable("folderSetId") Long folderSetId, @RequestBody FolderSet newFolderSet) {
        return folderSetService.editFolderSetById(folderSetId, newFolderSet);
    }

    //xóa một set ra khỏi một folder
    @DeleteMapping("/delete/{folderSetId}/set/{setId}")
    public ResponseEntity<?> deleteSetFromFolder(@PathVariable("folderSetId") Long folderSetId, @PathVariable("setId") Long setId) {
        return folderSetService.deleteSetFromFolder(folderSetId, setId);
    }
}
