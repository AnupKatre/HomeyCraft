package com.stackroute.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "users")
public class User {
	private String sellerId ;
	private String sellerName;
	private String sellerEmail;
	private String sellerPassword;
	private String sellerAddress;
	private String sellerPincode;
}
