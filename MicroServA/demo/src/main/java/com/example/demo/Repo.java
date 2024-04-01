package com.example.demo;
import com.example.demo.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<Book, Long> {
}