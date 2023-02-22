package com.stackroute.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
public class JwtRequest {

    @NotBlank
    @Size(min=3, max = 60)
    private String userName;

    @NotBlank
    @Size(min = 6, max = 40)
    private String userPassword;

}