package com.deltadc.quizletclone.settag;

import com.deltadc.quizletclone.set.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/set-tag")
public class SetTagController {
    private final SetTagService setTagService;

    //tao set tag moi
    @PostMapping("/create-set-tag")
    public ResponseEntity<?> createSetTag(@RequestBody SetTag setTag) {
        return setTagService.createSetTag(setTag);
    }

    //xoa set tag theo id
    @DeleteMapping("/{set_tag_id}")
    public ResponseEntity<?> deleteSetTag(@PathVariable("set_tag_id") Long set_tag_id) {
        return setTagService.deleteSetTag(set_tag_id);
    }

    //lay set-tag theo setId
    @GetMapping("/set/{setId}")
    public ResponseEntity<?> getSetTagBySetId(@PathVariable("setId") Long setId) {
        return setTagService.getSetTagBySetId(setId);
    }

    //lay tat ca cac set tag
    @GetMapping("/get-all-set-tags")
    public ResponseEntity<?> getAllSetTags() {
        return setTagService.getAllSetTags();
    }

    //lay ra tag name cua mot set
    @GetMapping("/set/{setId}/tag-name")
    public ResponseEntity<?> getTagNameBySetId(@PathVariable("setId") Long setId) {
        return setTagService.getTagNameBySetId(setId);
    }
}
