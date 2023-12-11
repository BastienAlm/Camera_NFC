/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.controller;

import com.gotham.cashmanagerApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotham.cashmanagerApp.models.User;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author zineditchoffo
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    //@Autowired
    private final BCryptPasswordEncoder BCryptPasswordEncoder;
    private final UserService service;
    
    

    @PostMapping("/create")
    public User create(@RequestBody User user){
        
       String encodedPassword = BCryptPasswordEncoder.encode(user.getPassword());
        
        user.setPassword(encodedPassword);
        return service.create(user);
    }
    
    @GetMapping("")
    public List<User> getUser(){
        return service.get();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public User updateById(@PathVariable Long id, @RequestBody User user){
            String encodedPassword = BCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return service.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "User deleted";
        }
}
