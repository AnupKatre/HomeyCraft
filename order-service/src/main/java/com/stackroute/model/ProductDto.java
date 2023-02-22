package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
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
}
