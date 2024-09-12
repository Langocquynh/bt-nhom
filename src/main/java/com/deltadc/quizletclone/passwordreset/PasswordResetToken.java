package com.deltadc.quizletclone.passwordreset;

import com.deltadc.quizletclone.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(nullable = false, name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private LocalDateTime expiryDate;


    public PasswordResetToken(String token, Long user_id, LocalDateTime expiryDate) {
        this.token = token;
        this.user_id = user_id;
        this.expiryDate = expiryDate;
    }
}
