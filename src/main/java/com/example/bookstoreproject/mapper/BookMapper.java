package com.example.bookstoreproject.mapper;

import org.mapstruct.Mapper;

import com.example.bookstoreproject.RequestResponseDto.BookRequestDto;
import com.example.bookstoreproject.RequestResponseDto.BookResponseDto;
import com.example.bookstoreproject.entity.Book;

@Mapper
public interface BookMapper {
    Book mapToEntity(BookRequestDto bookRequestDto);
    Book mapToEntity(BookResponseDto bookResponseDto);
    BookResponseDto mapToResponse(Book book);
}
