package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(CategoryRepr categoryRepr) {
        Category category = new Category();
        category.setId(categoryRepr.getId());
        category.setName(categoryRepr.getName());

        categoryRepository.save(category);
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryRepr> findById(Long id) {
        return  categoryRepository.findById(id).map(CategoryRepr::new);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
