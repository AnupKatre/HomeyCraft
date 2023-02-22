package com.stackroute.model;

import org.bson.types.Binary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
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
