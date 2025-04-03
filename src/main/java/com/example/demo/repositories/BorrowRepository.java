package com.example.demo.repositories;

import com.example.demo.models.Book;
import com.example.demo.models.Borrow;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    List<Borrow> findAllByUserId(Integer userId);

    List<Borrow> findAllByUserUsername(@NotBlank String username);

    List<Borrow> findAllByBook(Book book);
}
