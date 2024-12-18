package com.example.managementstore.controllers;

import com.example.managementstore.entities.ItemCategory;
import com.example.managementstore.exceptions.ResourceNotFoundException;
import com.example.managementstore.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ItemCategory createCategory(@RequestBody ItemCategory itemCategory) {
        return categoryService.createCategory(itemCategory);
    }

    @PutMapping("/update/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ItemCategory updateCategory(@PathVariable Long categoryId, @RequestBody ItemCategory itemCategory) throws ResourceNotFoundException {
        return categoryService.updateCategory(categoryId, itemCategory);
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ItemCategory getCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        return categoryService.getCategoryById(categoryId);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
    }
}
