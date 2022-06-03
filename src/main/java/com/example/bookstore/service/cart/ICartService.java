package com.example.bookstore.service.cart;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.CartData;

public interface ICartService {
    CartData addToCart(CartDTO cartDTO);

    Iterable<CartData> findAllCarts();

    CartData getCartById(int cartId);

    CartData updateCartQuantity(int cartId, int quantity);

    void deleteCart(int cartId);
}

