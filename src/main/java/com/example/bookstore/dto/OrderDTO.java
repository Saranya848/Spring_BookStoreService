package com.example.bookstore.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data
class OrderDTO
{
    public int userId;
    public int bookId;
//    public  int cartId;
    public String address;
    public LocalDate orderDate = LocalDate.now();
    public int totalPrice;
    public boolean cancel;
}
