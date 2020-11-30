package com.ecommerce.service.product;

import com.ecommerce.data.model.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);
    public Product findProductById(Integer id);
    public void deleteProductById(Integer id);
    public List<Product> findAllProducts();
    public Product updateProduct(Product product);

}
