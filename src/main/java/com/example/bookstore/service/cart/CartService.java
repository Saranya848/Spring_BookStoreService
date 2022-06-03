package com.example.bookstore.service.cart;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.userregistration.UserRegistrationCustomException;
import com.example.bookstore.model.BookData;
import com.example.bookstore.model.CartData;
import com.example.bookstore.model.UserRegistrationData;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRegistrationRepository;
import com.example.bookstore.service.books.IBookService;
import com.example.bookstore.service.userregistration.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    @Autowired
    private IBookService iBookService;

    @Override
    public CartData addToCart(CartDTO cartDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(cartDTO.getUserId());
        if (userRegistrationData != null) {
            BookData bookData = iBookService.getBookById(cartDTO.getBookId());
            CartData cartData = new CartData(userRegistrationData, bookData, cartDTO.quantity);
            return cartRepository.save(cartData);
        }
        return null;
    }

    @Override
    public Iterable<CartData> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartData getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new UserRegistrationCustomException("Cart with id " + cartId + " not found"));
    }

    @Override
    public CartData updateCartQuantity(int cartId, int quantity) {
        CartData cartData = this.getCartById(cartId);
        cartData.setQuantity(quantity);
        return cartRepository.save(cartData);
    }

    @Override
    public void deleteCart(int cartId) {
        CartData cartData = this.getCartById(cartId);
        cartRepository.delete(cartData);
    }
}
