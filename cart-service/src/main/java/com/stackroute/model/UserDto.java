package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
	private String buyerId ;
	private String buyerName;
	private String buyerEmail;
	private String buyerPassword;
	private String buyerAddress;
	private String buyerPincode;
}

