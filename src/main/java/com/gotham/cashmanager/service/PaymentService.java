/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanager.service;
import com.gotham.cashmanager.models.Payment;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface PaymentService {
    
   Payment create(Payment payment);
    
    Payment getById(Long id);
    
    List<Payment> getAll();
    
    Payment update(long id, Payment payement);
    
    String delete(long id);
    
    
}
