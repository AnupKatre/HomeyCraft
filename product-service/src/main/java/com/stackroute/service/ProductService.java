package com.stackroute.service;


import com.stackroute.exception.SellerEmailInvalidException;
import com.stackroute.model.Product;

import org.springframework.web.multipart.MultipartFile;



import java.util.List;




public interface ProductService {
	public Product addProduct(Product product) throws SellerEmailInvalidException;
	//Object addProductImage(String productName, MultipartFile multipartFile);
	Object addProductImage(String productId, MultipartFile multipartFile);
	public List<Product> getAllProduct();
	public Product getProductById(String productId);
	Product getProductBySellerEmail(String sellerEmail);
	public String deleteProductById(String productId);
	public Product updateProductById(Product product) throws SellerEmailInvalidException;


	//Product saveProduct(Product product,MultipartFile file) throws IOException;
}
