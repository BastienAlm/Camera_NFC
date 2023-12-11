/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.controller;

import com.gotham.cashmanagerApp.models.Cart;
import com.gotham.cashmanagerApp.models.User;
import com.gotham.cashmanagerApp.models.Product;
import com.gotham.cashmanagerApp.service.CartService;
import com.gotham.cashmanagerApp.service.UserService;
import com.gotham.cashmanagerApp.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    
  
     private final CartService service;
     private final UserService userService;
     private final ProductService productService;
     
    
     @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Cart> create(@RequestBody Cart cart, @PathVariable Long userId, @PathVariable Long productId){
        
        User user = userService.getById(userId);
        Product product = productService.getById(productId);
        
        
        cart.setUser(user);
        cart.setProduct(product);
        
        Cart newCart = service.create(cart);
        
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public List<Cart> getAllCarts(){
        return service.get();
    }
    @GetMapping("/user/{userId}")
    public List<Cart> getByUserId(@PathVariable Long userId){
        return service.getByUserId(userId);
    }
    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id){
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public Cart updateById(@PathVariable Long id, @RequestBody Cart cart){
            return service.update(id, cart);
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "Cart deleted";
        }
}
