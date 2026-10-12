package com.nawaf.lab12.Controller;

import com.nawaf.lab12.Api.ApiResponse;
import com.nawaf.lab12.Model.User;
import com.nawaf.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        authService.register(user);

        return ResponseEntity.status(201).body(new ApiResponse("Register successfully"));
    }
}
