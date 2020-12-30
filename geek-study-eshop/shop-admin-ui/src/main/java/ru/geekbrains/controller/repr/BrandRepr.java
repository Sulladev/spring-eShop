package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Product;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BrandRepr {

    private Long id;

    @NotEmpty
    private String name;

    private List<Product> products;

    public BrandRepr() {
    }

    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.products = brand.getProducts();
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
