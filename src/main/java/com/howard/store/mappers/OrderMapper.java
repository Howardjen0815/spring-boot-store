package com.howard.store.mappers;

import com.howard.store.dto.OrderDto;
import com.howard.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
