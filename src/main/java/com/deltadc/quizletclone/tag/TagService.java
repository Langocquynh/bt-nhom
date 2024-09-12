package com.deltadc.quizletclone.tag;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    // Lấy ra tất cả tag
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok(tagRepository.findAll());
    }

    // Lấy ra 1 tag
    public ResponseEntity<?> getTag(Long tag_id) {
        Tag tag = tagRepository.findById(tag_id).orElse(null);
        if (tag == null) {
            return ResponseEntity.badRequest().body("Cannot find this tag!");
        }
        return ResponseEntity.ok(tag);
    }

    public ResponseEntity<?> createTag(Tag newTag) {
        Tag tag = new Tag();
        tag.setName(newTag.getName());
        tagRepository.save(newTag);
        return ResponseEntity.ok(tag);
    }

    // Update 1 tag
    public ResponseEntity<?> updateTag(Long tag_id, Tag newTag) {
        Tag tag = tagRepository.findById(tag_id).orElse(null);
        if (tag == null) {
            return ResponseEntity.badRequest().body("Cannot find this tag!");
        }
        tag.setName(newTag.getName());
        tagRepository.save(tag);
        return ResponseEntity.ok(tag);
    }

    // Xóa 1 tag
    public ResponseEntity<?> deleteTag(Long tag_id) {
        Tag tag = tagRepository.findById(tag_id).orElse(null);
        if (tag == null) {
            return ResponseEntity.badRequest().body("Cannot find this tag!");
        }
        tagRepository.deleteById(tag_id);
        return ResponseEntity.ok("Deleted!");
    }
}
