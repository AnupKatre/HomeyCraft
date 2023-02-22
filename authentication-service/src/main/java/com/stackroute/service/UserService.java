package com.stackroute.service;


import com.stackroute.model.Role;
import com.stackroute.model.User;
import com.stackroute.repository.RoleDao;
import com.stackroute.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Seller");
        adminRole.setRoleDescription("!....I am a seller...!");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("Buyer");
        userRole.setRoleDescription("!....I am a Buyer....!");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("admin@123"));
      //  adminUser.setUserFirstName("admin");
      //  adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("user123@gmail.com");
        user.setUserPassword(getEncodedPassword("user@123"));
      //  user.setUserFirstName("user");
       // user.setUserLastName("user");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }

    public User loginBuyer(User user) {
        Role role = roleDao.findById("Buyer").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User loginSeller(User user) {
        Role role = roleDao.findById("Seller").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }



    public User getUser(String username){
        return this.userDao.findByUserName(username);
    }

}
