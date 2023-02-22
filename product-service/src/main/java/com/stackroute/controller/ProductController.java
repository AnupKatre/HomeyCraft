package com.stackroute.controller;


import java.util.List;


import com.stackroute.exception.ProductNotFoundException;
import com.stackroute.exception.SellerEmailInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.stackroute.model.Product;
import com.stackroute.producerConfig.ProducerConfig;
import com.stackroute.service.ProductService;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService service;

	@Autowired
	private RabbitTemplate template;

	@PostMapping(value = "/addproduct") // localhost:8092/product/addproduct
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SellerEmailInvalidException {
		template.convertAndSend(ProducerConfig.EXCHANGE1,ProducerConfig.ROUTING_KEY1, product);
		return new ResponseEntity<Product>(service.addProduct(product), HttpStatus.OK);
	}

	@PostMapping(value="/addimage/{productId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//localhost:8092/product/addimage/{productId}
	public ResponseEntity<Object> addProductImage(@PathVariable String productId,@RequestPart("image") MultipartFile multipartFile) throws ProductNotFoundException {
		ResponseEntity<Object> responseEntity;
		log.info("In controlller class to Add Info");

		Product productFromService= (Product) service.addProductImage(productId,multipartFile);
		responseEntity = new ResponseEntity<Object>(productFromService, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = "/products") // localhost:8092/product/products
	public ResponseEntity<List<Product>> getAllProduct() {
		return new ResponseEntity<List<Product>>(service.getAllProduct(), HttpStatus.OK);
	}

	@GetMapping(value = "/product/{productId}") // localhost:8092/product/product/{productId}
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		return new ResponseEntity<Product>(service.getProductById(productId), HttpStatus.OK);
	}

	@GetMapping(value = "/getbyemail/{sellerEmail}") // localhost:8092/product/getbyemail/{sellerEmail}
	public ResponseEntity getProductBySellerEmail(@PathVariable String sellerEmail) {
		return new ResponseEntity(service.getProductBySellerEmail(sellerEmail), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteproduct/{productId}") // localhost:8092/product/deleteproduct/{productId}
	public ResponseEntity<String> deleteProductById(@PathVariable String productId) {
		return new ResponseEntity<String>(service.deleteProductById(productId), HttpStatus.OK);
	}

	@PutMapping(value = "/updateproduct") // localhost:8092/product/updateproduct
	public ResponseEntity<Product> updateProductById(@RequestBody Product product) throws SellerEmailInvalidException {
		return new ResponseEntity<Product>(service.updateProductById(product), HttpStatus.OK);
	}

	//	@PostMapping(value="/add")
	//	public ProductSell saveProduct(@RequestParam("product")String productSell,@RequestParam("file") MultipartFile file) throws IOException{
	//		Gson gson = new Gson();
	//		ProductSell fromJson = gson.fromJson((productSell), ProductSell.class);
	//		return service.saveProduct(fromJson,file);
	//	}
}
