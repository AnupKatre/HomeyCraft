package com.stackroute.exception;

import java.util.NoSuchElementException;

public class CartNotFoundException extends NoSuchElementException {
    public CartNotFoundException() {

    }

    public CartNotFoundException(String s) {
        super(s);
    }

}
