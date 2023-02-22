package com.stackroute.controller;


import com.stackroute.model.JwtRequest;
import com.stackroute.model.JwtResponse;
import com.stackroute.service.JwtService;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    @GetMapping({"/Seller"})
    @PreAuthorize("hasRole('Seller')")
    public String forSeller(){
        return "Welcome to your Seller Dashboard";
    }

    @GetMapping({"/Buyer"})
    @PreAuthorize("hasRole('Buyer')")
    public String forBuyer(){
        return "Welcome to your Buyer Dashboard";
    }
}
