package com.nawaf.lab12.Controller;

import com.nawaf.lab12.Api.ApiResponse;
import com.nawaf.lab12.Model.User;
import com.nawaf.lab12.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getUsers(@AuthenticationPrincipal User admin) {
        return ResponseEntity.status(200).body(userService.getUsers(admin.getId()));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserById(@AuthenticationPrincipal User admin, @PathVariable Integer userId) {
        return ResponseEntity.status(200).body(userService.getUserById(admin.getId(), userId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody User bodyUser) {
        userService.updateUser(user.getId(),  bodyUser);

        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal User user) {
        userService.deleteUser(user.getId());

        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
}