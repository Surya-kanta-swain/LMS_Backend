package com.elms.controller;

import com.elms.entities.Leave;
import com.elms.entities.LeaveStatus;
import com.elms.entities.Request;
import com.elms.entities.User;
import com.elms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
public User signin(@RequestBody Request request){
        String email = request.getEmail();
        String password = request.getPassword();
        User user = userRepository.findByEmail(email);
        if(user != null){
            user.getPassword().equals(password);
            return user;
        }
        return null;
    }
    @PostMapping("/")
    public User createUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @GetMapping("/")
    public List<User> getLeaves(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User newUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setDepartment(newUser.getDepartment());
            user.setRole(newUser.getRole());
            user.setDays(newUser.getDays());
            userRepository.save(user);
        }
        return user;
    }




    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteUsers(){
        userRepository.deleteAll();
    }
}
