/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gotham.cashmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gotham.cashmanager.models.Account;

/**
 *
 * @author zineditchoffo
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

     Account findByAccountNumber(Long account_number);
    
}
