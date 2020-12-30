package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.repr.BrandRepr;
import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.CategoryService;

import javax.validation.Valid;


@Controller
public class CategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/category/create")
    public String adminCategoryCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new CategoryRepr());
        return "category_form";
    }

    @GetMapping("/category/{id}/edit")
    public String adminEditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryService.findById(id).orElseThrow(IllegalStateException::new));
        return "category_form";
    }

    @DeleteMapping("/category/{id}/delete")
    public String adminDeleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Categories");
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @PostMapping("/category")
    public String adminUpsertCategory(@Valid CategoryRepr category, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Categories");

        if (bindingResult.hasErrors()) {
            return "category_form";
        }
        try {
            categoryService.save(category);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating category", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (category.getId() == null) {
                return "redirect:/category/create";
            }
            return "redirect:/category/" + category.getId() + "/edit";
        }
        return "redirect:/categories";
    }
}
