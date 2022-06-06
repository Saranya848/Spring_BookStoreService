package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.OrderData;
import com.example.bookstore.service.orders.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    @GetMapping(value = {"", "/", "/getall"})
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderData> orderDataList = iOrderService.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Getting all orders", orderDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable("orderId") int orderId) {
        OrderData orderData = iOrderService.getOrderById(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Get order for id", orderData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/place_order")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderData orderData = iOrderService.placeOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully", orderData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/ordercancel/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable("orderId") int orderId) {
        iOrderService.cancelOrder(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Order canceled successfully", "Order id " + orderId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}

