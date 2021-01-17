package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.ProductException;
import com.ecommerce.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    default Product saveProduct(Product product) throws ProductException {
        if (isValid(product)){
            save(product);
        }
        return product;
    }

    private Boolean isValid(Product product) throws ProductException {
        if (!productHasName(product)){
            throw new ProductException("product must have a name");
        }
        if (!productHasPrice(product)){
           throw new ProductException("product must have a price");
        }
        if (!productHasExpDate(product)){
            throw new ProductException("product must have an expiry date");
        }
        if (!productHasQuantity(product)){
            throw new ProductException("product must have quantity");
        }
        return true;
    }

    private Boolean productHasName(Product product){
        if (product.getName() == null){
            return false;
        }
        return true;
    }

    private Boolean productHasPrice(Product product){
        if (product.getPrice() == null){
            return false;
        }
        return true;
    }

    private Boolean productHasExpDate(Product product){
        if (product.getExpDate() == null){
            return false;
        }
        return true;
    }

    private Boolean productHasQuantity(Product product){
        if (product.getQuantity() == null){
            return false;
        }
        return true;
    }

}
