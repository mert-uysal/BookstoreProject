package com.example.bookstoreproject.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "BOOKS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ISBN", unique = true)
    private String isbn;

    @Column(name = "BOOK_TITLE")
    private String title;

    @Column(name = "BOOK_AUTHOR")
    private String author;

    @Column(name = "BOOK_PRICE")
    private float price;

    @Column(name = "BOOK_STOCK_QUANTITY")
    private int stockQuantity;

    @Column(name = "BOOK_CREATE_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "BOOK_UPDATE_TIMESTAMP")
    private Timestamp updatedAt;
}
