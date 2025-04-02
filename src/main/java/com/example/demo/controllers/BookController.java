package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import com.example.demo.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public Book addBook(
            @RequestBody Book book
    ) {
        return bookService.addBook(book);
    }

    @GetMapping("/find/id/{serialNumber}")
    public Book getBookBySerialNumber(
            @PathVariable String serialNumber
    ) {
        return bookService.getBookBySerialNumber(serialNumber);
    }

    @GetMapping("/find/genre/{genre}")
    public List<Book> getBooksByGenre(
            @PathVariable Genre genre
    ) {
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping("/find/author/{authorName}")
    public List<Book> getBooksByAuthor(
            @PathVariable String authorName
    ) {
        return bookService.getBooksByAuthor(authorName);
    }

    @DeleteMapping("/delete/id/{serialNumber}")
    public void deleteBookBySerialNumber(
            @PathVariable String serialNumber
    ) {
        bookService.deleteBookBySerialNumber(serialNumber);
    }
}
