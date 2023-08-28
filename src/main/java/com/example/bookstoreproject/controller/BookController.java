package com.example.bookstoreproject.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreproject.RequestResponseDto.BookRequestDto;
import com.example.bookstoreproject.RequestResponseDto.BookResponseDto;
import com.example.bookstoreproject.entity.Book;
import com.example.bookstoreproject.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }

    @GetMapping("/{ISBN}")
    public ResponseEntity<BookResponseDto> getBookByISBN(@PathVariable String ISBN) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByISBN(ISBN));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(requestDto));
    }

    @PutMapping("/{ISBN}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable String ISBN, @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(ISBN, bookRequestDto));
    }

    @DeleteMapping("/{ISBN}")
    public ResponseEntity<String> deleteBook(@PathVariable String ISBN) {
        if (bookService.deleteBook(ISBN)) {
            return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }
}
