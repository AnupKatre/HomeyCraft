package com.stackroute.service;



import com.stackroute.model.*;
import com.stackroute.repository.UserDao;
import com.stackroute.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userDao.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    public User save(BuyerDto user) {

        User newUser = new User();
        newUser.setUserName(user.getBuyerEmail());
        newUser.setUserPassword(bcryptEncoder.encode(user.getBuyerPassword()));
        Set<Role> adminRoles = new HashSet<>();
        Role adminRole = new Role();
        adminRole.setRoleName("Buyer");
        adminRole.setRoleDescription("!....I am a Buyer...!");
        adminRoles.add(adminRole);
        newUser.setRole(adminRoles);
        return userDao.save(newUser);
    }

    public User save(SellerDto user) {

        User newUser = new User();
        newUser.setUserName(user.getSellerEmail());
        newUser.setUserPassword(bcryptEncoder.encode(user.getSellerPassword()));
        Set<Role> adminRoles = new HashSet<>();
        Role adminRole = new Role();
        adminRole.setRoleName("Seller");
        adminRole.setRoleDescription("!....I am a Seller...!");
        adminRoles.add(adminRole);
        newUser.setRole(adminRoles);
        return userDao.save(newUser);
    }
}
