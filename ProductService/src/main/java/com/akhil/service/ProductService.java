package com.akhil.service;

import com.akhil.entity.Product;

public interface ProductService {
    public Product getProductById(Integer id);

    public Product addNewProduct(Product product);

    public Product updateProduct(Product product);

    public Product deleteProduct(Integer id);

    public Iterable<Product> getAllProducts(Integer pageNum, Integer pageSize);

    public Iterable<Product> getOutOfStockProducts();

    public Iterable<Product> getProductsInPriceRange(Double min, Double max);

}