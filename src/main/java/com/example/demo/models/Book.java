package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String serialNumber;

    private String name;

    private int numberOfPages;

    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int availableCopies;

    private int totalCopies;

    @ManyToOne
    @JoinColumn
    private User user;

}
