package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductsController {

    @RequestMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("activePage", "None");
        return "products";
    }
}
