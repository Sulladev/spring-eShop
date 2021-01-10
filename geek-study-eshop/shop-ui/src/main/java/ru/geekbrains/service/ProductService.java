package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;

import java.util.List;

public interface ProductService {
    List<ProductRepr> findAll();
}
