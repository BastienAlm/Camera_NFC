/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gotham.cashmanagerApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author zineditchoffo
 */
@Entity
@Table(name = "Cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(length = 50)
    private Long quantity;
    @Column(length = 50)
    private Float price;
    
    @JsonIgnore
    @ManyToOne
    @OnDelete(action=OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId")
    private User user;
    
    @JsonIgnore
    @ManyToOne
    @OnDelete(action=OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "productId")
    private Product product;
}
