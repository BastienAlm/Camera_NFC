/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanagerApp.service;
import com.gotham.cashmanagerApp.models.User;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface UserService {
    
    User create(User user);
    
    User getById(Long id);
    
    User getByEmail(String email);
    
    List<User> get();
    
    User update(long id, User user);
    
    String delete(long id);
    
    
}
