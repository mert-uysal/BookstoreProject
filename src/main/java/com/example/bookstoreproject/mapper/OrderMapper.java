package com.example.bookstoreproject.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.bookstoreproject.RequestResponseDto.OrderRequestDto;
import com.example.bookstoreproject.RequestResponseDto.OrderResponseDto;
import com.example.bookstoreproject.entity.Order;

@Mapper
public interface OrderMapper {
    Order mapToEntity(OrderResponseDto orderResponseDto);
    OrderResponseDto mapToResponse(Order order);
    List<OrderResponseDto> mapToListResponse(List<Order> orderList);
}
