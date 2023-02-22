package com.stackroute.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.stackroute.exception.CustomerEmailInvalidException;
import com.stackroute.exception.ProductNotFoundException;
import com.stackroute.model.*;
import com.stackroute.rabbitMQConfig.ProducerConfig;
import com.stackroute.repository.ProductRepository;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.CartService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate template;


    public Cart updateCart(Cart cart) {
        float totalSum = 0;
        int count = 0;
        for(Product p:cart.getCartProducts().values()) {
            totalSum += p.getProductPrice()*cart.getProductQuantity().get(p.getProductId());
            count += cart.getProductQuantity().get(p.getProductId());
        }
        cart.setTotalCartValue(totalSum);
        cart.setTotalItems(count);
        return cart;
    }

    @Override
    public Cart addItemInCart(String customerEmail,String productId) throws CustomerEmailInvalidException, ProductNotFoundException {

        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerEmail);
//        if(existingCart.isPresent()) return existingCart.get();
//
//        return null;
        if(existingCart.isPresent()) {
            Cart cart = existingCart.get();
            Map<String,Integer> productQuantity = cart.getProductQuantity();
            Map<String,Product> products = cart.getCartProducts();
            if(!productRepository.existsById(productId)) throw new ProductNotFoundException();
            Product p = productRepository.findById(productId).get();
            if(products.containsKey(p.getProductId())) {
                int count = productQuantity.get(p.getProductId());
                productQuantity.put(p.getProductId(),count+1);
            }
            else {
                products.put(p.getProductId(), p);
                productQuantity.put(p.getProductId(), 1);
            }


            cart.setCartProducts(products);
            cart.setProductQuantity(productQuantity);

//            Cart c = cartRepository.save(updateCart(cart));
//            template.convertAndSend(ProducerConfig.EXCHANGE,"", c);
            return sendCartToRabbitMQ(cartRepository.save(updateCart(cart)));

        }
        throw new CustomerEmailInvalidException(customerEmail);

    }

    @Override
    public Cart removeItemInCart(String productId, String customerEmail) {
        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerEmail);

        if(existingCart.isPresent()) {
            Cart cart = existingCart.get();
            Map<String,Integer> map = cart.getProductQuantity();
            Map<String,Product> products = cart.getCartProducts();
            if(map.containsKey(productId)) {
                int count = map.get(productId);
                if(count >1) {
                    map.put(productId,count-1);
                }else {
                    map.remove(productId);
                    products.remove(productId);
                    cart.setCartProducts(products);
                }
                cart.setProductQuantity(map);
                return sendCartToRabbitMQ(cartRepository.save(updateCart(cart)));
            }else {
                System.out.println("Product which you want to remove from Cart, not present in Cart!");
            }
        }else {
            System.out.println("CustomerID is invalid.....");
        }

        return null;
    }

    @Override
    public Cart clearCart(String customerEmail) {
        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerEmail);
        if(existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setCartProducts(new HashMap<>());
            cart.setProductQuantity(new HashMap<>());
            cart.setTotalCartValue(0);
            cart.setTotalItems(0);
            return sendCartToRabbitMQ(cartRepository.save(cart));
        }else{
            System.out.println("Ivalid Customer Id!");
        }

        return null;
    }

    @Override
    public Cart getCartById(String customerEmail) {
        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerEmail);
        if(existingCart.isPresent()) return existingCart.get();

        System.out.println("Invalid Customer Id!");
        return null;
    }

    @Override
    public Product getProductById(String productId) {
        // TODO Auto-generated method stub
        Optional<Product> obj = productRepository.findById(productId);
        if(obj.isPresent()){
            return obj.get();
        }return null;
    }

    @Override
    public List<Product> getAllProduct(String customerEmail) throws CustomerEmailInvalidException {
        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerEmail);

        if(existingCart.isPresent()) {
            return (List<Product>)existingCart.get().getCartProducts().values();
        }
        throw new CustomerEmailInvalidException(customerEmail);

    }

    @Override
    public Cart deleteProductById(String productId, String customerId) {
        Optional<Cart> existingCart = cartRepository.findByCustomerEmail(customerId);

        if(existingCart.isPresent()) {
            Cart cart = existingCart.get();
            Map<String,Integer> productQuantity = cart.getProductQuantity();
            Map<String,Product> products = cart.getCartProducts();
            if(productQuantity.containsKey(productId)) {
                productQuantity.remove(productId);
                if(products.containsKey(productId)) products.remove(productId);

                cart.setCartProducts(products);
                cart.setProductQuantity(productQuantity);
                return sendCartToRabbitMQ(cartRepository.save(updateCart(cart)));
            }else {
                System.out.println("Already, there is no such product in cart");
            }
        }else {
            System.out.println("CustomerID is invalid.....");
        }

        return null;
    }




    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }


    public void createCartForNewUser(User user){
        Cart cart = new Cart();
        cart.setCustomerEmail(user.getBuyerEmail());
        cart.setCartProducts(new HashMap<String,Product>());
        cart.setProductQuantity(new HashMap<String,Integer>());
        cart.setTotalItems(0);
        cart.setTotalCartValue(0);
        cartRepository.save(cart);
    }

//------------------------------------------------------------------------------

    public Cart sendCartToRabbitMQ(Cart cart){
        template.convertAndSend(ProducerConfig.EXCHANGE,"", cart);
        return cart;
    }

//------------------------------------------------------------------------------

    @Override
    public void save(UserDto user) {
        User existingUser = new User();
        existingUser.setBuyerId(user.getBuyerId());
        existingUser.setBuyerName(user.getBuyerName());
        existingUser.setBuyerEmail(user.getBuyerEmail());
        existingUser.setBuyerPassword(user.getBuyerPassword());
        existingUser.setBuyerAddress(user.getBuyerAddress());
        existingUser.setBuyerPincode(user.getBuyerPincode());
        createCartForNewUser(userRepository.save(existingUser));
    }

    @Override
    public Product addProduct(ProductDto inputProduct) {
        Product product = new Product();
        product.setProductId(inputProduct.getProductId());
        product.setProductName(inputProduct.getProductName());
        product.setSellerEmail(inputProduct.getSellerEmail());
        product.setProductPrice(inputProduct.getProductPrice());
        product.setProductCategory(inputProduct.getProductCategory());
        product.setProductDescription(inputProduct.getProductDescription());
        product.setProductRatings(inputProduct.getProductRatings());
        product.setBrand(inputProduct.getBrand());
        product.setProductImage(inputProduct.getProductImage());
        product.setInventoryAmount(inputProduct.getInventoryAmount());
        return productRepository.save(product);
    }
}
