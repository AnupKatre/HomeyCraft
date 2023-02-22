package com.stackroute.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
	private String sellerId ;
	private String sellerName;
	private String sellerEmail;
	private String sellerPassword;
	private String sellerAddress;
	private String sellerPincode;
}

