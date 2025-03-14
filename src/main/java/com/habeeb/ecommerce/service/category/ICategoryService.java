package com.habeeb.ecommerce.service.category;

import java.util.List;

import com.habeeb.ecommerce.model.Category;

public interface ICategoryService {

    // Service method interface
    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);

}
