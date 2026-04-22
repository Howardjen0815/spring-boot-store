package com.howard.store.mappers;

import com.howard.store.dto.CartDto;
import com.howard.store.dto.CartItemDto;
import com.howard.store.entities.Cart;
import com.howard.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression="java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);


}
