package com.nawaf.lab12.Service;

import com.nawaf.lab12.Api.ApiException;
import com.nawaf.lab12.Model.User;
import com.nawaf.lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public List<User> getUsers(Integer adminId) {
        User admin = authRepository.findUserById(adminId);

        if(admin == null) throw new ApiException("Admin not found");

        return authRepository.findAll();
    }

    public User getUserById(Integer adminId, Integer userId){
        User admin = authRepository.findUserById(adminId);

        if(admin == null) throw new ApiException("Admin not found");
        return authRepository.findUserById(userId);
    }

    public void updateUser(Integer userId, User user){
        User foundUsername = authRepository.findUserByUsername(user.getUsername());
        User oldUser = authRepository.findUserById(userId);

        if(oldUser == null) throw new ApiException("User not found");
        if (foundUsername != null) throw new ApiException("Username already exist");

        user.setId(oldUser.getId());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);

        authRepository.save(user);
    }

    public void deleteUser(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null) throw new ApiException("User not found");

        authRepository.delete(user);
    }
}
