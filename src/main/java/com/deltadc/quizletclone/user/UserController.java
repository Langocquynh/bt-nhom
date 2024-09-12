package com.deltadc.quizletclone.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // danh dau class nay la RESTful Controller
@RequiredArgsConstructor // tự động tạo một constructor chứa tất cả các trường được đánh dấu là final hoặc @NonNull.
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUser_id(user.getUser_id());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    //lay ra toan bo user
    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    //xoa user theo id
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId) {
        return userService.deleteUserById(userId);
    }

    //edit mat khau cua 1 user theo userId
    @PutMapping("/change-password/{userId}")
    public ResponseEntity<?> changeUserPassWordById(@PathVariable("userId") Long userId, @RequestBody User newUser) {

        if(newUser.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc de trong");
        }

        User u = userService.changeUserPassWordById(userId, newUser);
        if(u == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc doi mat khau voi tai khoan khong phai cua minh");
        }

        UserDTO userDTO = convertToDTO(u);

        return ResponseEntity.ok(userDTO);
    }

    //lay user dua theo user id
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable("user_id") Long userId) {
        return userService.getUserById(userId);
    }

    //lay user dua theo username
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    //lay user dua theo email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/change-username/{userId}")
    public ResponseEntity<?> changeUsernameById(@PathVariable("userId") Long userId, @RequestBody User newUser) {

        if(newUser.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc de trong");
        }

        User u = userService.changeUsernameById(userId, newUser);

        if(u == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc doi ten voi tai khoan khong phai cua minh");
        }

        UserDTO userDTO = convertToDTO(u);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        return userService.forgotPassword(email);
    }

    @GetMapping("/confirm-reset-password")
    public String confirmResetPassword(@RequestParam("token") String token) {
        return userService.confirmResetPassword(token);
    }

    //edit mat khau cua 1 user theo userId
    @PutMapping("/reset-password/{userId}")
    public ResponseEntity<?> resetPasswordByUserId(@PathVariable("userId") Long userId, @RequestBody User newUser) {

        if(newUser.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc de trong");
        }

        User u = userService.resetPasswordByUserId(userId, newUser);
        if(u == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Khong duoc doi mat khau voi tai khoan khong phai cua minh");
        }

        UserDTO userDTO = convertToDTO(u);

        return ResponseEntity.ok(userDTO);
    }
}
