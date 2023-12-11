/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Cart;

import com.gotham.cashmanagerApp.repository.CartRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor 
public class CartServiceImp implements CartService{
    
    private final CartRepository repository;
    
    @Override
    public Cart create(Cart cart) {
       return repository.save(cart);
    }

    @Override
    public List<Cart> get() {
      return repository.findAll();     }

    @Override
    public Cart update(long id, Cart cart) {
        return repository.findById(id).map(u -> { u.setPrice(cart.getPrice()!= null ? cart.getPrice() : u.getPrice());
                                                  u.setQuantity(cart.getQuantity()!= null ? cart.getQuantity() : u.getQuantity()); 
                                              
                                                  return repository.save(u);
                                                }
                                            ).orElseThrow(() -> new RuntimeException("Cart non trouvé"));
    }

    @Override
    public String delete(long id) {
        repository.deleteById(id);
         return "Cart supprimé"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cart getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Cart non trouvé")); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public List<Cart> getByUserId(Long userId) {
         return repository.findByUserId(userId); //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
