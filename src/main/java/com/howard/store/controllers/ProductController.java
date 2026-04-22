package com.howard.store.controllers;

import com.howard.store.dto.ProductDTO;
import com.howard.store.entities.Product;
import com.howard.store.mappers.ProductMapper;
import com.howard.store.repositories.CategoryRepository;
import com.howard.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDTO> getAllProducts(
            @RequestParam(name = "categoryId", required = false) Byte categoryId
    ) {
        List<Product> products;
        if (categoryId == null) {
            products = productRepository.findAllWithCategory();
        }else{
            products = productRepository.findByCategoryId(categoryId);
        }
        return products.stream().map(productMapper::toDto).toList();
    }
    @GetMapping("/{id}")
    public  ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        var product = productRepository.findById(id).orElseThrow();
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO,
            UriComponentsBuilder uriComponentsBuilder
    ){
        var category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        if (category == null){
            return ResponseEntity.notFound().build();
        }
        var product = productMapper.toEntity(productDTO);
        product.setCategory(category);
        productRepository.save(product);
        productDTO.setId(product.getId());
        var uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ){
        var product = productRepository.findById(id).orElse(null);
        var category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        if (category == null){
            return ResponseEntity.notFound().build();
        }
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        product.setCategory(category);
        productMapper.update(productDTO, product);
        productRepository.save(product);
        productDTO.setId(product.getId());
        return ResponseEntity.ok(productDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }


}
