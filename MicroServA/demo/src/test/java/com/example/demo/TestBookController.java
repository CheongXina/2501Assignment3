package com.example.demo;


import com.example.demo.BookRepo;
import com.example.demo.Controller.BookController;
import com.example.demo.Models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")


public class TestBookController {

    @Mock
    private BookRepo bookRepo;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController();
        bookController.setBookRepo(bookRepo);
    }
    @Test
    public List<Book> getAllBooks() {
        return bookRepo.findAll(); // Return all books from the database
    }

    @Test
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book newBook = bookRepo.save(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Test
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Test
    public String testMsg() {
        return "Hello from Micro A";
    }


}
