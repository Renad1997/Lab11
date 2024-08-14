package com.example.lap11.Controller;

import com.example.lap11.Model.User;
import com.example.lap11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser() {
        return ResponseEntity.status(200).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors ) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User Added");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId,@Valid @RequestBody User user, Errors errors ){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(userId,user);

        return ResponseEntity.status(200).body("User Updated");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("User Deleted");

    }

    @GetMapping("/get/user/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) { // 3-extra endpoint
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) { // 4-extra endpoint
        List<User> u = userService.getUserByUsername(username);
      return ResponseEntity.status(200).body(u);

    }

}
