package com.example.bookstoreproject.RequestResponseDto;

import com.example.bookstoreproject.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Book book;
    private String message;
}
