package com.example.demo.controllers;

import com.example.demo.models.Borrow;
import com.example.demo.services.BorrowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/find/user/id/{userId}")
    public List<Borrow> getBorrowsByUserId(
            @PathVariable Integer userId
    ) {
        return borrowService.getBorrowsByUserId(userId);
    }

    @GetMapping("/find/user/username/{userName}")
    public List<Borrow> getBorrowsByUserName(
            @PathVariable String userName
    ) {
        return borrowService.getBorrowsByUserName(userName);
    }

    @GetMapping("/find/{id}")
    public Borrow getBorrowById(
            @PathVariable Integer id
    ) {
        return borrowService.getBorrowById(id);
    }

    @GetMapping("/find/book/id/{serialNumber}")
    public List<Borrow> getAllByBookSerialNumber(
            @PathVariable String serialNumber
    ) {
        return borrowService.getAllByBookSerialNumber(serialNumber);
    }

    @PostMapping("/borrow")
    public Borrow borrow(
            @RequestParam Integer userId,
            @RequestParam String serialNumber,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        return borrowService.borrow(userId, serialNumber, startDate, endDate);
    }

    @DeleteMapping("/return/{borrowId}")
    public void returnBorrow(
            @PathVariable Integer borrowId
    ) {
        borrowService.returnBorrow(borrowId);
    }

    @PutMapping("/extend")
    public Borrow extendBorrow(
            @RequestParam Integer borrowId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date newBorrowEndDate
    ) {
        return borrowService.extendBorrow(borrowId, newBorrowEndDate);
    }
}
