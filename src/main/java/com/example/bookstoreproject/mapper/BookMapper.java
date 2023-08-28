package com.example.bookstoreproject.mapper;

import org.mapstruct.Mapper;

import com.example.bookstoreproject.RequestResponseDto.BookRequestDto;
import com.example.bookstoreproject.RequestResponseDto.BookResponseDto;
import com.example.bookstoreproject.entity.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    Book mapToEntity(BookRequestDto bookRequestDto);
    Book mapToEntity(BookResponseDto bookResponseDto);
    BookResponseDto mapToResponse(Book book);
    BookRequestDto mapToRequest(Book book);
}
