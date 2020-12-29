package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CategoryRepr {

    private Long id;

    @NotEmpty
    private String name;

    private List<Product> products;

    public CategoryRepr() {
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
