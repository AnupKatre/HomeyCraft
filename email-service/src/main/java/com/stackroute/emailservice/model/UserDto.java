package com.stackroute.emailservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
	private String id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String pincode;
	private String userId;
}

