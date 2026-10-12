package com.nawaf.lab12.Service;

import com.nawaf.lab12.Api.ApiException;
import com.nawaf.lab12.Model.User;
import com.nawaf.lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public void register(User user){
        User foundUsername = authRepository.findUserByUsername(user.getUsername());

        if (foundUsername != null) throw new ApiException("Username already exist");

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);

        authRepository.save(user);
    }


}
