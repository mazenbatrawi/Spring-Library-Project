package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import com.example.demo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookBySerialNumber(String serialNumber) {
        return bookRepository.findById(serialNumber).orElse(null);
    }

    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    public void deleteBookBySerialNumber(String serialNumber) {
        bookRepository.deleteById(serialNumber);
    }
}
