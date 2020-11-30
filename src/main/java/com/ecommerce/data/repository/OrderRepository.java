package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    default Order saveOrder(Order order) throws OrderException {
       if (isValid(order)){
           save(order);
       }
        return order;
    }



    private Boolean isValid(Order order) throws OrderException {
        if (!orderHasCustomer(order)){
            throw new OrderException("Order must have a customer");
        }
        if (!orderHasProducts(order)){
            throw new OrderException("Order must have at least 1 product");
        }
        return true;
    }



    private boolean orderHasCustomer (Order order) {
        if (order.getCustomer() == null) {
           return false;
        }
        return true;
    }

    private boolean orderHasProducts (Order order) {
        if (order.getProducts() == null) {
            return false;
        }
        return true;
    }

}
