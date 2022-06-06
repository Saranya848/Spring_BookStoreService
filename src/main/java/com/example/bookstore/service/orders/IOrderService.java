package com.example.bookstore.service.orders;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.model.OrderData;

import java.util.List;

public interface IOrderService
{
    OrderData placeOrder(OrderDTO orderDTO);

    List<OrderData> getAllOrders();

    OrderData getOrderById(int orderId);

    OrderData cancelOrder(int orderId);
}