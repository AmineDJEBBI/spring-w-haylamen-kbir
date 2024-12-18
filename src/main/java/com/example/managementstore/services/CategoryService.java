package com.example.managementstore.services;

import com.example.managementstore.entities.ItemCategory;
import com.example.managementstore.exceptions.ResourceNotFoundException;
import com.example.managementstore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // Create a new itemCategory
    public ItemCategory createCategory(ItemCategory itemCategory) {
        return categoryRepository.save(itemCategory);
    }

    // Update an existing category
    public ItemCategory updateCategory(Long categoryId, ItemCategory updatedItemCategory) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            updatedItemCategory.setId(categoryId);
            return categoryRepository.save(updatedItemCategory);
        } else {
            throw new ResourceNotFoundException("ItemCategory not found");
        }
    }

    // Delete an existing category
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException("ItemCategory not found");
        }
    }

    public ItemCategory getCategoryById(Long categoryId) throws ResourceNotFoundException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCategory not found for this id :: " + categoryId));
    }

    // get all categories
    public List<ItemCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
