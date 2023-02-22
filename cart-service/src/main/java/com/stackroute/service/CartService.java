package com.stackroute.service;

import com.stackroute.exception.CustomerEmailInvalidException;
import com.stackroute.exception.ProductNotFoundException;
import com.stackroute.model.*;

import java.util.List;

public interface CartService {
    Cart addItemInCart(String customerEmail,String productId) throws CustomerEmailInvalidException, ProductNotFoundException;

    Cart removeItemInCart(String productId, String customerId);

    Cart clearCart(String customerId);

    Cart getCartById(String customerId);

    Product getProductById(String productId);

    List<Product> getAllProduct(String customerId) throws CustomerEmailInvalidException;

    Cart deleteProductById(String productId, String customerId);

    List<Cart> getAllCart();

    void save(UserDto user);

    Product addProduct(ProductDto product);
}
