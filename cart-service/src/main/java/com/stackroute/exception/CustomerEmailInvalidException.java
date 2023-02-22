package com.stackroute.exception;

public class CustomerEmailInvalidException extends Exception{
    public CustomerEmailInvalidException(String email) {
        super("Customer Email { "+email+" } is Invalid !!!!");
    }
}
