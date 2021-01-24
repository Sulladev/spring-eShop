package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        ProductServiceImpl impl = new ProductServiceImpl();
        impl.setProductRepository(productRepository);
        productService = impl;
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name");
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
    }

    @Test
    public void testFindAll() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct1 = new Product();
        expectedProduct1.setId(1L);
        expectedProduct1.setName("Product_1");
        expectedProduct1.setCategory(expectedCategory);
        expectedProduct1.setBrand(expectedBrand);
        expectedProduct1.setPictures(new ArrayList<>());
        expectedProduct1.setPrice(new BigDecimal(123));

        Product expectedProduct2 = new Product();
        expectedProduct2.setId(2L);
        expectedProduct2.setName("Product_2");
        expectedProduct2.setCategory(expectedCategory);
        expectedProduct2.setBrand(expectedBrand);
        expectedProduct2.setPictures(new ArrayList<>());
        expectedProduct2.setPrice(new BigDecimal(456));

        List<Product> products = new ArrayList<>();
        products.add(expectedProduct1);
        products.add(expectedProduct2);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductRepr> result = productService.findAll();
        verify(productRepository, atLeastOnce()).findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());

        assertEquals(expectedProduct1.getId(),result.get(0).getId());
        assertEquals(expectedProduct2.getName(), result.get(1).getName());

    }

}