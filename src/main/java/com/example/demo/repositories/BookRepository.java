package com.example.demo.repositories;

import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByGenre(Genre genre);

    List<Book> findAllByAuthor(String author);
}
