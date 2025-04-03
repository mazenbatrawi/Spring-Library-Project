package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Borrow> borrows;

}
