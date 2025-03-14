package com.habeeb.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.habeeb.ecommerce.exceptions.ProductNotFoundException;
import com.habeeb.ecommerce.model.Category;
import com.habeeb.ecommerce.model.Product;
import com.habeeb.ecommerce.repository.CategoryRepository;
import com.habeeb.ecommerce.repository.ProductRepository;
import com.habeeb.ecommerce.request.AddProductRequest;
import com.habeeb.ecommerce.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    // Repository Injection
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override //Get product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override // Add product
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    // Creating a product
    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDesc(),
                category);
    }

    @Override // Delete product by id
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("Product not found!");
        });
    }

    @Override // Update Product
    public Product updateProduct(UpdateProductRequest request, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    // Updating the existing product
    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDesc(request.getDesc());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setQuantity(request.getQuantity());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override // Get product by category
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override // Get product by brand
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override // Get product by category and brand
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override // Get product by name
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override // Get product by brand and name
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override // Count product by brand and name
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
