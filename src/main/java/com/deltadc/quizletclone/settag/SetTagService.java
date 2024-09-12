package com.deltadc.quizletclone.settag;

import com.deltadc.quizletclone.set.Set;
import com.deltadc.quizletclone.set.SetRepository;
import com.deltadc.quizletclone.tag.Tag;
import com.deltadc.quizletclone.tag.TagRepository;
import com.deltadc.quizletclone.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SetTagService {
    private final SetTagRepository setTagRepository;
    private final SetRepository setRepository;
    private final TagRepository tagRepository;

    public ResponseEntity<?> createSetTag(SetTag setTag) {
        Long set_id = setTag.getSet_id();
        Long tag_id = setTag.getTag_id();

        Set set = setRepository.findById(set_id).orElse(null);
        if (set == null) {
            return ResponseEntity.badRequest().body("Cannot create set tag because there is not set!");
        }
        Tag tag = tagRepository.findById(tag_id).orElse(null);
        if (tag == null) {
            return ResponseEntity.badRequest().body("Cannot create set tag because there is not tag!");
        }
        SetTag newSetTag = new SetTag();
        newSetTag.setSet_id(set.getSet_id());
        newSetTag.setTag_id(tag.getTag_id());
        setTagRepository.save(newSetTag);
        return ResponseEntity.ok(newSetTag);
    }

    public ResponseEntity<?> deleteSetTag(Long set_tag_id) {
        SetTag setTag = setTagRepository.findById(set_tag_id).orElse(null);
        if (setTag == null) {
            return ResponseEntity.badRequest().body("Cannot find this set tag!");
        }
        setTagRepository.deleteById(set_tag_id);
        return ResponseEntity.ok("Deleted!");
    }

    public ResponseEntity<?> getSetTagBySetId(Long setId) {
        SetTag setTag = setTagRepository.findBySetId(setId);

        return ResponseEntity.ok(setTag);
    }

    public ResponseEntity<?> getAllSetTags() {
        List<SetTag> setTagList = setTagRepository.findAll();

        return ResponseEntity.ok(setTagList);
    }

    public ResponseEntity<?> getTagNameBySetId(Long setId) {
        SetTag setTag = setTagRepository.findBySetId(setId);

        Long tag_id = setTag.getTag_id();

        Tag t = tagRepository.findById(tag_id).orElseThrow();

        return ResponseEntity.ok(t.getName());
    }
}
