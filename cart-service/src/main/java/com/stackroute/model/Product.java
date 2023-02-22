package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Products_cart")
public class Product {
    @Id
    private String productId;
    private String productName;
    private String sellerEmail;
    private long productPrice;
    private String productCategory;
    private String productDescription;
    private int productRatings;
    private String brand;
    private Binary productImage;
    private long inventoryAmount;
//    private int quantity;
//    private long sellingPrice;
}
