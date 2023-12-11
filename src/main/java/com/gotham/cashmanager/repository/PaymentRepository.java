/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gotham.cashmanager.models.Payment;

/**
 *
 * @author zineditchoffo
 */
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
    
}
