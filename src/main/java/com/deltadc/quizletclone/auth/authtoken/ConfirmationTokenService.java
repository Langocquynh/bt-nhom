package com.deltadc.quizletclone.auth.authtoken;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public List<ConfirmationToken> findExpiredTokens(LocalDateTime now) {
        // Assuming ConfirmationTokenRepository is used to access ConfirmationToken entities
        List<ConfirmationToken> allTokens = confirmationTokenRepository.findAll();

        // Filter tokens that have expired
        List<ConfirmationToken> expiredTokens = allTokens.stream()
                .filter(token -> token.getExpiresAt().isBefore(now))
                .collect(Collectors.toList());

        return expiredTokens;
    }

    public void deleteConfirmationToken(ConfirmationToken token) {
        Optional<ConfirmationToken> c = confirmationTokenRepository.findByToken(String.valueOf(token));
        c.ifPresent(confirmationTokenRepository::delete);
    }
}
