package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.service.CategoryService;
import org.java.spring.db.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PhotoService photoService;
    
    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }
    
    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "newCategory";
    }
    
    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            return "newCategory";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        
        Category category = categoryService.findById(id);
        
        List<Photo> categoryPhotos = category.getPhotos();
        categoryPhotos.forEach(photo -> {
            
            photo.getCategories().remove(category);
            photoService.save(photo);
        });
        
        categoryService.delete(category);
        
        return "redirect:/categories";
    }

}
