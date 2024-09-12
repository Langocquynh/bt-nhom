package com.deltadc.quizletclone.review;

import com.deltadc.quizletclone.set.SetRepository;
import com.deltadc.quizletclone.user.User;
import com.deltadc.quizletclone.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final SetRepository setRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private boolean isReviewOwner(Review r) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByEmail(username).orElseThrow();
        Long userId = user.getUser_id();

        return Objects.equals(userId, r.getUser_id());
    }

    public ResponseEntity<Double> getSetAverageRating(@PathVariable Long setId) {
        List<Review> setReviews = reviewRepository.findReviewsBySetId(setId);

        int totalStars = 0;
        int numberOfReviews = setReviews.size();

        for (Review review : setReviews) {
            totalStars += review.getTotalStars();
        }

        double averageRating = numberOfReviews > 0 ? (double) totalStars / numberOfReviews : 0.0;

        return ResponseEntity.ok(averageRating);
    }


    public ResponseEntity<?> postSetReviews(@PathVariable Long setId, @RequestBody Review review) {
        Long userId = review.getUser_id();

        // Kiểm tra xem user đã review set này trước đó chưa
        boolean hasReviewed = reviewRepository.existsBySetIdAndUserId(setId, userId);
        if (hasReviewed) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng đã review set này trước đó");
        }

        boolean isOwner = setRepository.findById(setId).get().getUser_id() == userId;
        if(isOwner) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong duoc tao review voi set cua ban than");
        }

        Review createdReview = new Review();
        createdReview.setSet_id(review.getSet_id());
        createdReview.setUser_id(review.getUser_id());
        createdReview.setTotalStars(review.getTotalStars());

        reviewRepository.save(createdReview);

        return ResponseEntity.ok(createdReview);
    }

    public ResponseEntity<?> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return ResponseEntity.ok(reviews);
    }

    public ResponseEntity<?> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);

        return ResponseEntity.ok(reviews);
    }

    public ResponseEntity<?> editReviewById(Long reviewId, Review newReview) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        if(!isReviewOwner(review)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Không được sửa review nếu không phải chủ sở hữu");
        }

        review.setTotalStars(newReview.getTotalStars());

        reviewRepository.save(review);

        return ResponseEntity.ok(review);
    }

    public ResponseEntity<?> deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        if(!isReviewOwner(review)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Không được xóa review nếu không phải chủ sở hữu");
        }

        reviewRepository.delete(review);

        return ResponseEntity.ok("Đã xóa review");
    }

    public ResponseEntity<?> getReviewByUserIdAndSetId(Long setId, Long userId) {
        Review review = reviewRepository.findBySetIdAndUserId(setId, userId);

        return ResponseEntity.ok(review);
    }
}
