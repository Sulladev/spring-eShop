package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.service.CategoryService;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    private ProductRepository productRepository;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @GetMapping("/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/category/{id}/edit")
    public String adminEditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("products", productRepository.findAll());
        return "category_form";
    }

    @GetMapping("/category/create")
    public String adminCreateCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new CategoryRepr());
        model.addAttribute("products", productRepository.findAll());
        return "category_form";
    }

    @PostMapping("/category")
    public String adminUpsertCategory(@Valid CategoryRepr category, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Categories");

        if (bindingResult.hasErrors()) {
            return "category_form";
        }

        categoryService.save(category);
        return "redirect:/categories";
    }

    @DeleteMapping("/category/{id}/delete")
    public String adminDeleteCategory(Model model, @PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
