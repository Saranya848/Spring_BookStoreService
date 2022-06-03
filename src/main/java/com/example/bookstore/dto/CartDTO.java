package com.example.bookstore.dto;

import lombok.Data;

public @Data class CartDTO {
    public int userId;
    public int bookId;
//    public String userId;
//    public String bookId;
    public int quantity;
}
