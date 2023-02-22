package com.stackroute.model;

import java.io.Serializable;
import java.util.UUID;


import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Products")
public class Product implements Serializable{
	@Id
	private String productId = UUID.randomUUID().toString();
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

