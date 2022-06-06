package com.example.bookstore.service.orders;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.userregistration.UserRegistrationCustomException;
import com.example.bookstore.model.BookData;
import com.example.bookstore.model.OrderData;
import com.example.bookstore.model.UserRegistrationData;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.books.IBookService;
import com.example.bookstore.service.userregistration.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    @Autowired
    private IBookService iBookService;

    @Override
    public OrderData placeOrder(OrderDTO orderDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(orderDTO.getUserId());
        BookData bookData = iBookService.getBookById(orderDTO.getBookId());
        OrderData orderData = new OrderData(userRegistrationData, bookData, orderDTO);
        return orderRepository.save(orderData);
    }

    @Override
    public List<OrderData> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderData getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new UserRegistrationCustomException("Order with id " + orderId + " not found"));
    }

    @Override
    public OrderData cancelOrder(int orderId) {
        OrderData orderData = this.getOrderById(orderId);
        orderData.setCancel(true);
        return orderRepository.save(orderData);
    }
}

