package com.stackroute.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;
import java.util.UUID;



@NoArgsConstructor
@Data
@Document(collection="CartData")
public class Cart {
    @Id
    private String id = UUID.randomUUID().toString();
    private String customerEmail;
    private Map<String,Product> cartProducts;
    private Map<String,Integer> productQuantity;
    private int totalItems;
    private float totalCartValue;

}
