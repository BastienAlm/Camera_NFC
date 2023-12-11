/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author zineditchoffo
 */
@Entity
@Table(name = "Product", uniqueConstraints = {@UniqueConstraint(columnNames = "productName")})
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(length = 50)
    private String productName;
    @Column(length = 50)
    private String picture;
    @Column(length = 50)
    private Long price;
    @Column(length = 50)
    private String description;
    
    
    @OneToMany(mappedBy = "product")
    private Set<Cart> cart = new HashSet<>();

}
