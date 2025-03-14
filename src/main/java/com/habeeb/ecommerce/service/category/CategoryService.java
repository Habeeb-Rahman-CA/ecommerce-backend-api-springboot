package com.habeeb.ecommerce.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.habeeb.ecommerce.exceptions.AlreadyExistException;
import com.habeeb.ecommerce.exceptions.ResourceNotFoundException;
import com.habeeb.ecommerce.model.Category;
import com.habeeb.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override // Get category by id
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override // Get category by name
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override // Add category
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException(category.getName() + " already exists"));
    }

    @Override // Update category
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override // Delete category by id
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Category not found");
        });
    }

}
