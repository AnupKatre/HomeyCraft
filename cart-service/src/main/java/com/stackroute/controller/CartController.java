package com.stackroute.controller;

import com.stackroute.exception.CustomerEmailInvalidException;
import com.stackroute.exception.ProductNotFoundException;
import com.stackroute.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.service.CartService;

@RestController
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private CartRepository repo;



    @PostMapping("/addItem")
    public ResponseEntity addItemInCart(@RequestParam("customerEmail") String email, @RequestParam("productId") String productId ) throws CustomerEmailInvalidException, ProductNotFoundException {
//        System.out.println(cart.toString());
        ResponseEntity responseEntity = new ResponseEntity(service.addItemInCart(email,productId), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity removeItemInCart(@RequestParam("productId") String productId, @RequestParam("customerEmail") String customerEmail) {
        ResponseEntity responseEntity = new ResponseEntity(service.removeItemInCart(productId,customerEmail), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/clearCart")
    public ResponseEntity clearCart(@RequestParam("customerEmail") String customerEmail) {
        ResponseEntity responseEntity = new ResponseEntity(service.clearCart(customerEmail), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getCart")
    public ResponseEntity getCartByEmail(@RequestParam("customerEmail") String customerEmail) {
        ResponseEntity responseEntity = new ResponseEntity(service.getCartById(customerEmail), HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteProductById(@RequestParam("productId") String productId, @RequestParam("customerEmail") String customerEmail) {
        ResponseEntity responseEntity = new ResponseEntity(service.deleteProductById(productId,customerEmail), HttpStatus.OK);
        return responseEntity;
    }
//------------------------------------------------------------------------//

    @GetMapping("/product/{productId}")
    public ResponseEntity getProductById(@RequestParam("productId") String productId) {
        ResponseEntity responseEntity = new ResponseEntity(service.getProductById(productId), HttpStatus.OK);
        return responseEntity;
    }

//    @GetMapping("/showAllProductInCart")
//    public ResponseEntity getAllProduct(@RequestParam("customerEmail") String customerEmail) throws CustomerEmailInvalidException {
//        ResponseEntity responseEntity = new ResponseEntity(service.getAllProduct(customerEmail), HttpStatus.OK);
//        return responseEntity;
//    }

    @GetMapping("/showAllCart")
    public ResponseEntity getAllProduct(){
        return new ResponseEntity(service.getAllCart(),HttpStatus.OK);
    }



//	public ResponseEntity getCartByEmail(@RequestParam("customerEmail") String customerEmail) {
//		ResponseEntity responseEntity = new ResponseEntity(service.getCartByEmail(customerEmail), HttpStatus.OK);
//		return responseEntity;
//	}
}