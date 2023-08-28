package com.example.bookstoreproject.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.bookstoreproject.RequestResponseDto.OrderRequestDto;
import com.example.bookstoreproject.RequestResponseDto.OrderResponseDto;
import com.example.bookstoreproject.entity.Order;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(target = "user.id", source = "userId")
    Order mapToEntity(OrderRequestDto orderRequestDto);
    OrderResponseDto mapToResponse(Order order);
    List<OrderResponseDto> mapToListResponse(List<Order> orderList);
}
