/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.controller;

import com.gotham.cashmanager.models.User;
import com.gotham.cashmanager.service.UserServiceimpl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotham.cashmanager.models.Account;
import com.gotham.cashmanager.service.AccountService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author zineditchoffo
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    
    
    private final AccountService service;
    
    private UserServiceimpl userService;
  
    
    @PostMapping("/create/{userId}")
    public ResponseEntity<Account> create(@RequestBody Account account, @PathVariable Long userId){
        
        User user = userService.getById(userId);
        
        account.setUser(user);
        
        Account newAccount = service.create(account);
        
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }
    
    @GetMapping("/getAll")
    public List<Account> getAccount(){
        return service.getAll();
    }
    @GetMapping("/getById/{id}")
    public Account getAccount(@PathVariable Long id){
        return service.getById(id);
    }
    @GetMapping("/getByAccount/{id}")
    public Account getByAccountNumber(@PathVariable Long id){
        return service.getByAccountNumber(id);
    }
    
    @PutMapping("/updateById/{id}")
    public Account updateById(@PathVariable Long id, @RequestBody Account account){
            return service.update(id, account);
    }
    
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "User deleted";
        }
}
