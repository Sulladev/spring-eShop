package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.controller.repr.RoleRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductRepr productRepr);

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);

    void delete(Long id);
}
