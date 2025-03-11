package com.castro.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String isbn;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private BookGenre genre;

    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private Author author;

}