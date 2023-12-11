/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanagerApp.repository;

import com.gotham.cashmanagerApp.models.Cart;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zineditchoffo
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    
}
