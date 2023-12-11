/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanager.service;
import com.gotham.cashmanager.models.Account;
import java.util.List;

/**
 *
 * @author zineditchoffo
 */
public interface AccountService {
    
   Account create(Account account);
    
    Account getById(Long id);
    
    Account getByAccountNumber(Long account);
    
    List<Account> getAll();
    
    Account update(long id, Account account);
    
    String delete(long id);
    
    
}
