/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Bill;

import com.gotham.cashmanagerApp.repository.BillRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor 
public class BillServiceImp implements BillService{
    
    private final BillRepository repository;
    
    @Override
    public Bill create(Bill bill) {
       return repository.save(bill);
    }

    @Override
    public List<Bill> get() {
      return repository.findAll();     }

  /*  @Override
    public Bill update(long id, Bill bill) {
        return repository.findById(id).map(u -> { u.setPrice(cart.getPrice()!= null ? cart.getPrice() : u.getPrice());
                                                  u.setQuantity(cart.getQuantity()!= null ? cart.getQuantity() : u.getQuantity()); 
                                              
                                                  return repository.save(u);
                                                }
                                            ).orElseThrow(() -> new RuntimeException("Cart non trouvé"));
    }*/

    @Override
    public String delete(long id) {
        repository.deleteById(id);
         return "Facture supprimé"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bill getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Facture non trouvé")); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public List<Bill> getByUserId(Long userId) {
         return repository.findByUserId(userId); //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
