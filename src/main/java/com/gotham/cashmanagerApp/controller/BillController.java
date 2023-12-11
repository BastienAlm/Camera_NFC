/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.controller;

import com.gotham.cashmanagerApp.models.Bill;


import com.gotham.cashmanagerApp.models.User;
import com.gotham.cashmanagerApp.service.BillService;
import com.gotham.cashmanagerApp.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bill")
@AllArgsConstructor
public class BillController {
    
  
     private final BillService service;
      private final UserService userService;
     

     @PostMapping("/{userId}")
    public ResponseEntity<Bill> create(@RequestBody Bill bill, @PathVariable Long userId){
        
        User user = userService.getById(userId);
        
        bill.setUser(user);
       
        
        Bill newBill = service.create(bill);
        
        return new ResponseEntity<>(newBill, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/")
    public List<Bill> getAllBills(){
        return service.get();
    }
    @GetMapping("/user/{userId}")
    public List<Bill> getByUserId(@PathVariable Long userId){
        return service.getByUserId(userId);
    }
    @GetMapping("/{id}")
    public Bill getBill(@PathVariable Long id){
        return service.getById(id);
    }
    
   /* @PutMapping("/updateById/{id}")
    public Bill updateById(@PathVariable Long id, @RequestBody Bill bill){
            return service.update(id, bill);
    }*/
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        service.delete(id);
        return "Cart deleted";
        }
}
