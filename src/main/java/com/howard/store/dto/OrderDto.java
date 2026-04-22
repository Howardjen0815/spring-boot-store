package com.howard.store.dto;

import com.howard.store.entities.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private long id;
    private PaymentStatus status;
    private LocalDateTime createAt;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;

}
