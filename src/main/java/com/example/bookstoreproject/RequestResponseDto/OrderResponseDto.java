package com.example.bookstoreproject.RequestResponseDto;

import java.sql.Timestamp;
import java.util.List;

import com.example.bookstoreproject.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long userId;
    private float totalPrice;
    private List<Book> books;
    private Timestamp orderDate;
    private String message;
}
