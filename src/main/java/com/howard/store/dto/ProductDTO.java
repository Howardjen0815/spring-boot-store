package com.howard.store.dto;

import lombok.*;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;

}
