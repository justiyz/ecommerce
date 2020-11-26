package com.ecommerce.service.order;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order) throws OrderException;
    public Order findOrderById(Integer id);
    public void deleteOrderById(Integer id);
    public List<Order> findAllOrders();
    public Order updateOrder(Order order) throws OrderException;
}
