package com.example.bookstoreproject.RequestResponseDto;

import java.util.List;
import java.util.PrimitiveIterator;

import com.example.bookstoreproject.entity.Book;
import com.example.bookstoreproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private Long orderId;
    private User userId;
    private float totalPrice;
    private List<Book> books;
}
