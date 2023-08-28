package com.example.bookstoreproject.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstoreproject.RequestResponseDto.OrderRequestDto;
import com.example.bookstoreproject.RequestResponseDto.OrderResponseDto;
import com.example.bookstoreproject.entity.Order;
import com.example.bookstoreproject.mapper.OrderMapper;
import com.example.bookstoreproject.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderResponseDto> getAllOrders() {
        return orderMapper.mapToListResponse(orderRepository.findAll());
    }

    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findOrdersByUserIdOrderByUpdatedAtDesc(userId);
        return orderMapper.mapToListResponse(orderList);
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
            .userId(orderRequestDto.getUserId())
            .totalPrice(orderRequestDto.getTotalPrice())
            .books(orderRequestDto.getBooks())
            .orderDate(currentTimestamp)
            .message("Order saved successfully")
            .build();
        orderRepository.save(orderMapper.mapToEntity(orderResponseDto));
        return orderResponseDto;
    }
}
