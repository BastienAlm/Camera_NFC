/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Cart;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface CartService {
    
        
    Cart create(Cart cart);
    
    Cart getById(Long id);
    
    List<Cart> getByUserId(Long userId);
    
    List<Cart> get();
    
    Cart update(long id, Cart cart);
    
    String delete(long id);
    
    
}
