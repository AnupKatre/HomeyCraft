package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.model.Buyer;
import com.stackroute.emailservice.repository.BuyerUserRepository;
import com.stackroute.emailservice.repository.SellerUserRepository;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@RestController
@RequestMapping("/email")

public class Emailcontroller {
    @Autowired
    private EmailService emailService;

    @Autowired
    private BuyerUserRepository buyerRepository;

    @Autowired
    private SellerUserRepository sellerRepository;


    public Emailcontroller(EmailService emailService) {
        this.emailService = emailService;
    }

//    @GetMapping("/showmsg")
//    public String showMsg(){
//        return "Hello From Email";
//    }

//    @GetMapping("/welcome")
//    public String welcome(){
//        Optional<Buyer> tempBuyer = buyerRepository.findByBuyerName("anup");
//        if(tempBuyer.isPresent()){
//            Buyer buyer = tempBuyer.get();
//            emailService.sendSimpleMessage(buyer.getBuyerEmail(),"order placed","susccfully done");
//            return "this is my email api";
//        }
//        else{
//            return "User name Invalid!!!";
//        }
//    }

    @PostMapping("/sendregisteremail")
    @CrossOrigin(origins = "http://localhost:4200")
    public String sendRegisterEmail(@RequestParam("User_Email") String to){

        this.emailService.sendSimpleMessage(to, "Registration Confirmation!!", "You have Successfully Registred to homycraft!");

        return "Thanks for the registration!\n" +
                "Your account has been created and a verification email has been sent to your registered email \n" +
                "address.\n" +
                " Please click on the verification linkincluded in the email to activate your account.\n" +
                "                                              Return to Sign In";
    }

    @PostMapping("/orderstatus")
    @CrossOrigin(origins = "http://localhost:4200")
    public String orderStatus(@RequestParam("User_Email") String to){

        this.emailService.sendSimpleMessage(to, "Order Status!", "Your order has been Successfully done to homycraft!");

//        return"order placed Susccfully!";
        return  "Homycraft\n" +
                "It's ordered!\n" +
                "Hi Seray - thanks for your order, we hope you enjoyed shopping with\n" +
                "us.\n" +
                "Thank you for Shoping with us\n\n\n"+
               "For Any Queries \n"+ "Please contacke <service.helpdesk@homeycraft.com>";
    }

    @PostMapping("/sendsimpleemail")
    @CrossOrigin(origins = "http://localhost:4200")
    public String sendsimpleemail(@RequestParam("User_Email") String to){
        System.out.println(to);

        this.emailService.sendSimpleMessage(to, "Registration Confirmation!!", "You have Successfully Registred to homycraft!");

        return "New notification from Homeycraft!!!";
    }
}




