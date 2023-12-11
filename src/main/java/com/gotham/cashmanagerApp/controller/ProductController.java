/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.controller;

import com.gotham.cashmanagerApp.models.Product;
import com.gotham.cashmanagerApp.service.ProductService;

import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zineditchoffo
 */

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    
  
     private final ProductService service;

    @PostMapping("")
    public Product create(@RequestBody Product product){
        
        return service.create(product);
    }
    
    @GetMapping("")
    public List<Product> getProduct(){
        return service.get();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public Product updateById(@PathVariable Long id, @RequestBody Product product){
            return service.update(id, product);
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "Product deleted";
        }
}
