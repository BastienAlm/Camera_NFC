/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.controller;

import com.gotham.cashmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotham.cashmanager.models.User;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author zineditchoffo
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    
    private final UserService service;
    
    @PostMapping("/create")
    public User create(@RequestBody User user){
        return service.create(user);
    }
    
    @GetMapping("/getAll")
    public List<User> getUser(){
        return service.get();
    }
    @GetMapping("/getById/{id}")
    public User getUser(@PathVariable Long id){
        return service.getById(id);
    }
    
    @PutMapping("/updateById/{id}")
    public User updateById(@PathVariable Long id, @RequestBody User user){
            return service.update(id, user);
    }
    
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "User deleted";
        }
}
