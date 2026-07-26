package com.LibraryManagement.LibraryManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.validation.annotation.Validated;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bookid")
    private Long id;

    private String isbn;
    private String title;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
    private String publisher;
    private int publishedYear;
    private int quantity;
    private double price;


}
