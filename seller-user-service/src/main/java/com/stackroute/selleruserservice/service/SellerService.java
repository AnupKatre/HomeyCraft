package com.stackroute.selleruserservice.service;

import com.stackroute.selleruserservice.Exception.SellerNotFoundException;
import com.stackroute.selleruserservice.model.Seller;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SellerService {


    Seller saveSeller(Seller seller);

    List<Seller> getAllSellerDetails();

	String deleteSeller(Seller seller);

    ResponseEntity<Seller> getByEmail(String email) throws SellerNotFoundException;

}
