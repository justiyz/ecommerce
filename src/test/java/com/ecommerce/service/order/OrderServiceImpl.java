package com.ecommerce.service.order;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public Order saveOrder(Order order) throws OrderException {
        return orderRepository.saveOrder(order);
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return null;
    }

    @Override
    public Order updateOrder(Order order) throws OrderException {
        return orderRepository.saveOrder(order);
    }
}
