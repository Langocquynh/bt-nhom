package com.deltadc.quizletclone.review;

import com.deltadc.quizletclone.set.Set;
import com.deltadc.quizletclone.user.User;
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
@Table(name = "review")
public class Review {

    public Review(Long user_id, Long set_id, int totalStars) {
        this.totalStars = totalStars;
        this.set_id = set_id;
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long review_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "set_id", nullable = false, insertable = false, updatable = false)
    private Set set;

    @Column(name = "total_stars", nullable = false)
    private int totalStars;

    private Long set_id;
    private Long user_id;

}
