/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanagerApp.service;

import com.gotham.cashmanagerApp.models.Bill;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface BillService {
    
     Bill create(Bill bill);
    
    Bill getById(Long id);
    
   List<Bill> getByUserId(Long userId);
    
    List<Bill> get();
    
    //Bill update(long id, Bill bill);
    
    String delete(long id);
    
}
