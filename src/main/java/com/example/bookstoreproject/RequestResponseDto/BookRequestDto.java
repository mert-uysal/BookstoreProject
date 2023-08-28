package com.example.bookstoreproject.RequestResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    private String isbn;
    private String title;
    private String author;
    private float price;
    private int stockQuantity;
}
