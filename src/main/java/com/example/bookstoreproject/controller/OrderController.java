package com.example.bookstoreproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreproject.RequestResponseDto.OrderRequestDto;
import com.example.bookstoreproject.RequestResponseDto.OrderResponseDto;
import com.example.bookstoreproject.entity.Order;
import com.example.bookstoreproject.entity.User;
import com.example.bookstoreproject.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orderList = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrderByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderRequestDto));
    }
}
