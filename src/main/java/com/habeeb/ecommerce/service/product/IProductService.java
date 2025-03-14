package com.habeeb.ecommerce.service.product;

import java.util.List;

import com.habeeb.ecommerce.model.Product;
import com.habeeb.ecommerce.request.AddProductRequest;
import com.habeeb.ecommerce.request.UpdateProductRequest;

public interface IProductService {

    // Service methods interface
    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest product, Long prodId);

    List<Product> getAllProducts();

    List<Product> getProductByCategory(String category);

    List<Product> getProductByBrand(String brand);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductByBrandAndName(String brand, String name);
    
    Long countProductByBrandAndName(String brand, String name);
}
