package com.example.bookstoreproject.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bookstoreproject.RequestResponseDto.OrderRequestDto;
import com.example.bookstoreproject.RequestResponseDto.OrderResponseDto;
import com.example.bookstoreproject.entity.Book;
import com.example.bookstoreproject.entity.Order;
import com.example.bookstoreproject.mapper.BookMapper;
import com.example.bookstoreproject.mapper.OrderMapper;
import com.example.bookstoreproject.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final BookService bookService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookMapper bookMapper;

    public List<OrderResponseDto> getAllOrders() {
        return orderMapper.mapToListResponse(orderRepository.findAll());
    }

    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findOrdersByUserIdOrderByUpdatedAtDesc(userId);
        return orderMapper.mapToListResponse(orderList);
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToEntity(orderRequestDto);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        order.setCreatedAt(currentTimestamp);
        order.setUpdatedAt(currentTimestamp);
        List<Book> updatedBooks = orderRequestDto.getBooks().stream()
            .map(book -> {
                int newStockQuantity = book.getStockQuantity() - 1;
                book.setStockQuantity(newStockQuantity);
                bookService.updateBook(book.getIsbn(), bookMapper.mapToRequest(book));
                orderRequestDto.setTotalPrice(orderRequestDto.getTotalPrice() + book.getPrice());
                return book;
            })
            .collect(Collectors.toList());
        orderRequestDto.setBooks(updatedBooks);

        try {
            if (orderRequestDto.getTotalPrice() >= 25) {
                orderRepository.save(order);

                return OrderResponseDto.builder()
                    .userId(orderRequestDto.getUserId())
                    .totalPrice(orderRequestDto.getTotalPrice())
                    .books(orderRequestDto.getBooks())
                    .orderDate(currentTimestamp)
                    .message("Order saved successfully")
                    .build();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            return OrderResponseDto.builder().message(e + "- Order Price Not Enough").build();
        }
    }
}
