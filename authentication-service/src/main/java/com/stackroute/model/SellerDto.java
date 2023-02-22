package com.stackroute.model;

import lombok.*;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SellerDto {

    private String sellerId;
    private String sellerName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerAddress;
    private String sellerPincode;
}
