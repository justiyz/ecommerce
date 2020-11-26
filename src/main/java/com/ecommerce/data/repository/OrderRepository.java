package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public default Order saveOrder(Order order) throws OrderException {
        if (!isOrderValid(order)){
            throw new OrderException("Customer can not be null");
        }
        else if (!orderHasProducts(order)){
            throw new OrderException("Order must have products");
        }

        save(order);
        return order;
    }



    private boolean isOrderValid(Order order){
        if (order.getCustomer() == null){
            return false;
        }
        return true;
    }

    private boolean orderHasProducts(Order order){
        if (order.getProducts() == null){
            return false;
        }
        return true;
    }
}
