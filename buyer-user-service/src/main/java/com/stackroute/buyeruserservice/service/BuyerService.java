package com.stackroute.buyeruserservice.service;

import com.stackroute.buyeruserservice.Exception.BuyerNotFoundException;
import com.stackroute.buyeruserservice.model.Buyer;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface BuyerService {
    Buyer saveBuyer(Buyer buyer);

    List<Buyer> getAllBuyerDetails();

    ResponseEntity<Buyer> getByEmail(String email) throws BuyerNotFoundException;

    String deleteBuyer(Buyer buyer);


}
