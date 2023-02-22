package com.stackroute.exception;

public class SellerEmailInvalidException extends Exception{
    public SellerEmailInvalidException(String email) {
        super("Seller Email { "+email+" } is Invalid !!!!");
    }
}
