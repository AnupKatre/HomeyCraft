package com.stackroute.model;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "users")
public class User {
	private String buyerId ;
	private String buyerName;
	private String buyerEmail;
	private String buyerPassword;
	private String buyerAddress;
	private String buyerPincode;
}
