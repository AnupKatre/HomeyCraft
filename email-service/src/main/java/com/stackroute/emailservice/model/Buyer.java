package com.stackroute.emailservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buyer_details")
public class Buyer {
    @Id
    private String buyerId = UUID.randomUUID().toString();
    private String buyerName;
    private String buyerEmail;
    private String buyerPassword;
    private String buyerAddress;
    private String buyerPincode;

}