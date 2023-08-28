package com.example.bookstoreproject.service;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookstoreproject.RequestResponseDto.BookRequestDto;
import com.example.bookstoreproject.RequestResponseDto.BookResponseDto;
import com.example.bookstoreproject.entity.Book;
import com.example.bookstoreproject.mapper.BookMapper;
import com.example.bookstoreproject.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private BookMapper bookMapper;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public BookResponseDto getBookByISBN(String isbn) {
        return bookMapper.mapToResponse(bookRepository.findBookByIsbn(isbn));
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapToEntity(bookRequestDto);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        book.setCreatedAt(currentTimestamp);
        book.setUpdatedAt(currentTimestamp);

        bookRepository.save(book);

        return BookResponseDto.builder()
            .book(book)
            .message("Book created successfully")
            .build();
    }

    public BookResponseDto updateBook(String ISBN, BookRequestDto updatedBook) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Book existingBook = bookMapper.mapToEntity(getBookByISBN(ISBN));
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setUpdatedAt(currentTimestamp);

            BookResponseDto bookResponseDto = bookMapper.mapToResponse(bookRepository.save(existingBook));
            bookResponseDto.setMessage("Book updated successfully");
            return bookResponseDto;
        }
        return null;
    }

    public boolean deleteBook(String ISBN) {
        if (getBookByISBN(ISBN) != null) {
            bookRepository.deleteBookByIsbn(ISBN);
            return true;
        } else {
            return false;
        }
    }

}
