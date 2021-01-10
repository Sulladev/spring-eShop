package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.ProductService;

@Controller
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }
}
