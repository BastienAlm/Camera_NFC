/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanager.controller;

import com.gotham.cashmanager.service.PaymentService;
import com.gotham.cashmanager.service.AccountServiceImp;
import com.gotham.cashmanager.models.Account;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotham.cashmanager.models.Payment;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author zineditchoffo
 */
@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
    
    
    private final PaymentService service;
    
    private final AccountServiceImp accountService;
    

    
    @PostMapping("/create/{accountNumber}/{accountKey}/{destinationAccount}/{sum}")
    public ResponseEntity<Object> create( @PathVariable Long accountNumber,@PathVariable Long accountKey, @PathVariable Long destinationAccount, @PathVariable Long sum ){
        
        Account account = accountService.getByAccountNumber(accountNumber);
        
        Account account2 = accountService.getByAccountNumber(destinationAccount);
        
        
        
        
        if (accountKey == account.getAccountKey()){
            
            if (sum <= account.getMaxPayment()){
                
             if (sum <= account.getAmount()){
                if(account2 != null ){
                     
                    account.setAmount(account.getAmount() - sum );
                    account2.setAmount(account2.getAmount() + sum);
                    Payment payment = new Payment();
                    payment.setAccount(account);
                    payment.setDestination_account(destinationAccount);
                    payment.setUser(account.getUser());
                     
                    return new ResponseEntity<> (service.create(payment), HttpStatus.CREATED);
                     
                }
                else return new ResponseEntity<> ("Invalid destination account.", HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<> ("Insufficient balance", HttpStatus.BAD_REQUEST);
        }else return new ResponseEntity<> ("Max payment amount excided", HttpStatus.BAD_REQUEST);
            
           
        }else return new ResponseEntity<> ("Invalid credentials", HttpStatus.FORBIDDEN);
    }
    
    @GetMapping("/getAll")
    public List<Payment> getUser(){
        return service.getAll();
    }
    @GetMapping("/getById/{id}")
    public Payment getUser(@PathVariable Long id){
        return service.getById(id);
    }
    
    @PutMapping("/updateById/{id}")
    public Payment updateById(@PathVariable Long id, @RequestBody Payment payment){
            return service.update(id, payment);
    }
    
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "User deleted";
        }
}
