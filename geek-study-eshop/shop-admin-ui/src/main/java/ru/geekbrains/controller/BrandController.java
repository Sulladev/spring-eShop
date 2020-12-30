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
import ru.geekbrains.controller.repr.RoleRepr;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.service.BrandService;

import javax.validation.Valid;

@Controller
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public String adminBrandsPage(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }

    @GetMapping("/brand/create")
    public String adminBrandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new BrandRepr());
        return "brand_form";
    }

    @GetMapping("/brand/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", brandService.findById(id).orElseThrow(NotFoundException::new));
        return "brand_form";
    }

    @DeleteMapping("/brand/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        brandService.delete(id);
        return "redirect:/brands";
    }

    @PostMapping("/brand")
    public String adminUpsertBrand(@Valid BrandRepr brand, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Brands");

        if (bindingResult.hasErrors()) {
            return "brand_form";
        }

        try {
            brandService.save(brand);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (brand.getId() == null) {
                return "redirect:/brand/create";
            }
            return "redirect:/brand/" + brand.getId() + "/edit";
        }
        return "redirect:/brands";
    }
}
