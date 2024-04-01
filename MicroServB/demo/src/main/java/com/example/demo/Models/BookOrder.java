package com.example.demo.Models;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class BookOrder {
    @Id
    private Long id;
    private Long bookId;


}
