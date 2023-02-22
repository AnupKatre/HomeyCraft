package com.stackroute.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;

}
