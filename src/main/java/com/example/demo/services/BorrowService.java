package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.models.Borrow;
import com.example.demo.models.User;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public List<Borrow> getBorrowsByUserId(Integer userId) {
        return borrowRepository.findAllByUserId(userId);
    }

    public List<Borrow> getBorrowsByUserName(String userName) {
        return borrowRepository.findAllByUserUsername(userName);
    }

    public Borrow getBorrowById(Integer id) {
        return borrowRepository.findById(id).orElse(null);
    }

    public List<Borrow> getAllByBookSerialNumber(String bookSerialNumber) {
        Book book = bookRepository.findById(bookSerialNumber).orElse(null);
        if (book == null) {
            return null;
        }
        return borrowRepository.findAllByBook(book);
    }

    @Transactional
    public Borrow borrow(Integer userId, String bookSerialNumber, Date borrowDate, Date returnDate) {
        Book book = bookRepository.findById(bookSerialNumber).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (book == null || user == null) {
            throw new RuntimeException("Book or user not found");
        }
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        else {
            throw new RuntimeException("Book is out of stock");
        }
        bookRepository.save(book);
        Borrow borrow = Borrow.builder()
                .user(user)
                .book(book)
                .startDate(borrowDate)
                .endDate(returnDate)
                .build();
        return borrowRepository.save(borrow);
    }

    @Transactional
    public void returnBorrow(Integer borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId).orElse(null);
        if (borrow == null) {
            throw new RuntimeException("Borrow not found");
        }
        Book book = borrow.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        borrowRepository.delete(borrow);
    }

    public Borrow extendBorrow(Integer borrowId, Date newBorrowEndDate) {
        Borrow borrow = borrowRepository.findById(borrowId).orElse(null);
        if (borrow == null) {
            throw new RuntimeException("Borrow not found");
        }
        borrow.setEndDate(newBorrowEndDate);
        return borrowRepository.save(borrow);
    }

}
