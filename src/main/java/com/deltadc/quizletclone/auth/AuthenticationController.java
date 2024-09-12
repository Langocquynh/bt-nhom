package com.deltadc.quizletclone.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // If the email matches the regex pattern, return true; otherwise, return false
        return pattern.matcher(email).matches();
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SignUpRequest request) {
        if(request.getUsername().isEmpty() || request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            AuthenticationResponse.builder()
                                    .message("khong duoc de trong")
                                    .build()
                    );
        }

        if(!isValidEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(
                            AuthenticationResponse.builder()
                                    .message("email khong hop le")
                                    .build()
                    );
        }


        return authenticationService.signup(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        if(request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            AuthenticationResponse.builder()
                                    .message("khong duoc de trong")
                                    .build()
                    );
        }

        if(!isValidEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(
                            AuthenticationResponse.builder()
                                    .message("email khong hop le")
                                    .build()
                    );
        }


        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
