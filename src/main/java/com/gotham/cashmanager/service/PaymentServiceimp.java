/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.service;

import com.gotham.cashmanager.models.Payment;
import com.gotham.cashmanager.repository.PaymentRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author zineditchoffo
 */
@Service
@AllArgsConstructor 
public class PaymentServiceimp implements PaymentService{
    
    private final PaymentRepository repository;
    
    public Payment create(Payment payment) {
       return repository.save(payment);
    }
    

    public List<Payment> getAll() {
      return repository.findAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   //     return repository.findById(id).map(u -> { 
    //        u.setAccount_key(payment.getAccount_key());
     //       u.setAccount_number(account.getAccount_number());
       //     u.setMax_payment(account.getMax_payment());
         //   
        
           //             return repository.save(u);}).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    //}

 
    public String delete(long id) {
        repository.deleteById(id);
         return "Utilisateur supprimé"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Payment getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("payement not found")); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Payment update(long id, Payment payement) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
