package com.howard.store.mappers;

import com.howard.store.dto.ProductDTO;
import com.howard.store.entities.Product;
import com.howard.store.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
    @Mapping(target = "id", ignore = true)
    void update(ProductDTO productDTO, @MappingTarget Product product);
}
