package com.stackroute.selleruserservice.controller;

import com.stackroute.selleruserservice.Exception.SellerNotFoundException;
import com.stackroute.selleruserservice.SellerConfig.SellerConfig;
import com.stackroute.selleruserservice.model.Seller;
import com.stackroute.selleruserservice.service.SellerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;

	@Autowired
	private RabbitTemplate template;

	@Autowired
	public SellerController(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	//@GetMapping("/showmsg")
	//public String showMsg(){
		//template.convertAndSend(SellerConfig.EXCHANGE1, SellerConfig.ROUTING_KEY1, "MSG from seller");
		//return "Hello From Seller";
//	}


	@PostMapping("/saveseller")
	public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
		template.convertAndSend(SellerConfig.EXCHANGE1,SellerConfig.ROUTING_KEY1, seller);
		return new ResponseEntity<Seller>(sellerService.saveSeller(seller), HttpStatus.CREATED);
	}
	@GetMapping("/get/{sellerEmail}")
	public ResponseEntity<Seller> getByEmail(@PathVariable("sellerEmail") String email) throws  SellerNotFoundException {
		return sellerService.getByEmail(email) ;
	}
	@GetMapping("/getAllseller")
	public List<Seller> getAllSellerDetails() {
		return sellerService.getAllSellerDetails();
	}

	@PutMapping("/update/{sellerEmail}")
	public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
		return new ResponseEntity<Seller>(sellerService.saveSeller(seller), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteSeller")

	public String deleteSeller(@RequestBody Seller seller) {
		return (sellerService.deleteSeller(seller));
	}

//	@GetMapping("/getsellerbyemail/{sellerEmail}")
//	public List<Seller> getAllSellerEmailId() {
//		return sellerService.getAllSellerDetails();
//	}






}
