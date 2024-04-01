package com.example.demo;
import com.example.demo.Models.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<BookOrder, Long> {
}