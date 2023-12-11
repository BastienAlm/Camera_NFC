/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Product;
import com.gotham.cashmanagerApp.repository.ProductRepository;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author zineditchoffo
 */
@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository repository;
    
    @Override
    public Product create(Product product) {
       return repository.save(product);
    }

    @Override
    public List<Product> get() {
      return repository.findAll();
    }

    @Override
    public Product update(long id, Product user) {
        return repository.findById(id).map(u -> { u.setProductName(user.getProductName()!= null ? user.getProductName() : u.getProductName());
                                                  u.setPicture(user.getPicture()!= null ? user.getPicture() : u.getPicture()); 
                                                  u.setPrice(user.getPrice()!= null ? user.getPrice() : u.getPrice());
                                                  u.setDescription(user.getDescription()!=null ? user.getDescription() : u.getDescription());
                                                  return repository.save(u);
                                                }
                                            ).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String delete(long id) {
        repository.deleteById(id);
         return "Utilisateur supprimé"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found")); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //public User getByEmail(String email) {
    //    return repository.findByEmail(email);
    //}
    
}
