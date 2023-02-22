package com.stackroute.buyeruserservice.controller;

import com.stackroute.buyeruserservice.Exception.BuyerNotFoundException;
import com.stackroute.buyeruserservice.model.Buyer;
import com.stackroute.buyeruserservice.rabbitMQ.BuyerConfig;
import com.stackroute.buyeruserservice.rabbitMQ.ProducerConfig;
import com.stackroute.buyeruserservice.service.BuyerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @Autowired
    RabbitTemplate template;


   // @GetMapping("/showmsg")
    //public String showMsg(){
      //  template.convertAndSend(BuyerConfig.EXCHANGE,BuyerConfig.ROUTING_KEY,"This is the message from sender");
        //return "Hello From buyer";
    //}

    @PostMapping("/savebuyer")
    public ResponseEntity<Buyer> saveBuyer(@RequestBody Buyer buyer) {
        template.convertAndSend(BuyerConfig.EXCHANGE,BuyerConfig.ROUTING_KEY, buyer);
        return new ResponseEntity<Buyer>(buyerService.saveBuyer(buyer), HttpStatus.CREATED);
    }
    //@GetMapping("/get/{buyerEmail}")
	///public List<Buyer> getBuyerList() {
	//	return buyerService.getAllBuyerDetails();
	//}
    @GetMapping("/get/{BuyerEmail}")
    public ResponseEntity<Buyer> getByEmail(@PathVariable("BuyerEmail") String email) throws BuyerNotFoundException {
        return buyerService.getByEmail(email) ;
    }
	@PutMapping("/update")
	public ResponseEntity<Buyer> updateBuyer(@RequestBody Buyer buyer) {
		return new ResponseEntity<Buyer>(buyerService.saveBuyer(buyer), HttpStatus.CREATED);
	}

	
	//@DeleteMapping("/deleteBuyer/{buyerEmail}")
	//public String deleteBuyer(@PathVariable("buyerEmail") String buyerEmail) {
	//	return buyerService.deleteBuyer(buyerEmail);
	//}
    @DeleteMapping("/deleteBuyer")
    public String deleteBuyer(@RequestBody Buyer buyer) {
        return buyerService.deleteBuyer(buyer);
    }



    @GetMapping("/getallbuyer")
    public List<Buyer> getAllBuyerDetails() {
        return buyerService.getAllBuyerDetails();
    }
}
