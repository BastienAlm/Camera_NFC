/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.service;

import com.gotham.cashmanager.models.User;
import com.gotham.cashmanager.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author zineditchoffo
 */
@Service
@AllArgsConstructor 
public class UserServiceimpl implements UserService{
    
    private final UserRepository repository;
    
    @Override
    public User create(User user) {
       return repository.save(user);
    }

    @Override
    public List<User> get() {
      return repository.findAll();     }

    @Override
    public User update(long id, User user) {
        return repository.findById(id).map(u -> { u.setName(user.getName());
                        return repository.save(u);}).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String delete(long id) {
        repository.deleteById(id);
         return "Utilisateur supprimé"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found")); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
