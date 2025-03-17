package com.habeeb.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habeeb.ecommerce.model.Product;
import com.habeeb.ecommerce.request.AddProductRequest;
import com.habeeb.ecommerce.request.UpdateProductRequest;
import com.habeeb.ecommerce.response.ApiResponse;
import com.habeeb.ecommerce.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        try{
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        try{
            Product theProduct = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Found", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try{
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Success", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProductById(@PathVariable Long id, @RequestBody UpdateProductRequest product){
        try{
            Product theProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Found", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(Long id){
        try{
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Found", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/brand-and-product")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try{
            List<Product> products = productService.getProductByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try{
            List<Product> products = productService.getProductByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
        try{
            List<Product> products = productService.getProductByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
        try{
            List<Product> products = productService.getProductByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category){
        try{
            List<Product> products = productService.getProductByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/count/brand-and-product")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try{
            var productCount = productService.countProductByBrandAndName(brandName, productName);
            return ResponseEntity.ok(new ApiResponse("Found", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
