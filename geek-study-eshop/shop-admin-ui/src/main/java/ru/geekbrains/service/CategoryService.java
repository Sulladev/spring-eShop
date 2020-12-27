package ru.geekbrains.service;

import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.controller.repr.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void save(CategoryRepr categoryRepr);

    List<CategoryRepr> findAll();

    Optional<CategoryRepr> findById(Long id);

    void delete(Long id);
}
