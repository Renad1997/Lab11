package com.example.lap11.Service;

import com.example.lap11.Api.ApiException;
import com.example.lap11.Model.User;
import com.example.lap11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer userId ,User user) {
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistration_date(user.getRegistration_date());
        userRepository.save(u);
    }

    public void deleteUser(Integer userId) {
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    public User getUserByEmail(String email) { //3-extra endpoint
        User u = userRepository.pleaseSearchUserByEmail(email);
        if(u==null){
            throw new ApiException("User by email not found");
        }
        return u;
    }

    public List<User> getUserByUsername(String username) { //4-extra endpoint
        List<User> u = userRepository.pleaseSearchUserByUsername(username);
        if(u.isEmpty()){
            throw new ApiException("User by username not found");
        }
        return u;
    }


}
