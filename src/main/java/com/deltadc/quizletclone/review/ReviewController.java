package com.deltadc.quizletclone.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;

    private boolean isInvalidReview(Review review) {
        return  review.getTotalStars() > 5
                || review.getTotalStars() < 0;
    }

    //trả về tất cả review của một set
    @GetMapping("/sets/{set_id}/reviews")
    public ResponseEntity<Double> getSetAverageRating(@PathVariable("set_id") Long setId) {
        return reviewService.getSetAverageRating(setId);
    }

    // tạo một review mới
    @PostMapping("/sets/{set_id}/review")
    public ResponseEntity<?> postSetReviews(@PathVariable("set_id") Long setId, @RequestBody Review review) {
        if(isInvalidReview(review)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Review không hợp lệ");
        }

        return reviewService.postSetReviews(setId, review);
    }

    //lay tat ca cac review
    @GetMapping("/get-all-reviews")
    public ResponseEntity<?> getAllReviews() {
        return reviewService.getAllReviews();
    }

    //lay tat ca review theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReviewsByUserId(@PathVariable("userId") Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    //sua mot review theo id
    @PutMapping("/edit/{reviewId}")
    public ResponseEntity<?> editReviewById(@PathVariable("reviewId") Long reviewId, @RequestBody Review newReview) {
        if(isInvalidReview(newReview)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Review mới không hợp lệ");
        }

        return reviewService.editReviewById(reviewId, newReview);
    }

    //xoa mot review theo id
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("reviewId") Long reviewId) {
        return reviewService.deleteReviewById(reviewId);
    }

    //lay review theo userId va setId
    @GetMapping("/set/{setId}/user/{userId}")
    public ResponseEntity<?> getReviewByUserIdAndSetId(@PathVariable("setId") Long setId, @PathVariable("userId") Long userId) {
        return reviewService.getReviewByUserIdAndSetId(setId, userId);
    }

}
