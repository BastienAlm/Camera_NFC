/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.service;

import com.gotham.cashmanager.models.Account;
import com.gotham.cashmanager.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author zineditchoffo
 */
@Service
@AllArgsConstructor 
public class AccountServiceImp implements AccountService{
    
    private final AccountRepository repository;
    
    @Override
    public Account create(Account account) {
       return repository.save(account);
    }

    @Override
    public List<Account> getAll() {
      return repository.findAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account update(long id, Account account) {
        return repository.findById(id).map(u -> { 
            u.setAccountKey(account.getAccountKey());
           // u.setAccount(account.getAccount());
            u.setMaxPayment(account.getMaxPayment());
            return repository.save(u);}).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

  
    public String delete(long id) {
        repository.deleteById(id);
         return "Utilisateur supprimé"; 
    }

    @Override
    public Account getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found")); 
    }
    
    public Account getByAccountNumber(Long accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }
    
}
