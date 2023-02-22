package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Document(collection = "Cart")
public class NewCartDto {
    private String id ;
    private String customerEmail;
    private Map<String, ProductDto> cartProducts;
    private Map<String,Integer> productQuantity;
    private int totalItems;
    private float totalCartValue;
}
