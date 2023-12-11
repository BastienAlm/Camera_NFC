/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Product;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface ProductService {
    
      Product create(Product product);
    
    Product getById(Long id);
    
   // Product getByEmail(String email);
    
    List<Product> get();
    
    Product update(long id, Product product);
    
    String delete(long id);
    
    
}
