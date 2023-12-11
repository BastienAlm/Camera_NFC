/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanager.service;
import com.gotham.cashmanager.models.User;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface UserService {
    
    User create(User user);
    
    User getById(Long id);
    
   
    
    List<User> get();
    
    User update(long id, User user);
    
    String delete(long id);
    
    
}
