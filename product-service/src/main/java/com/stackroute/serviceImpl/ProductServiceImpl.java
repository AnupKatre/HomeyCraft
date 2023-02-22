package com.stackroute.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.stackroute.exception.IdNotFoundException;
import com.stackroute.exception.ProductNotFoundException;
import com.stackroute.exception.SellerEmailInvalidException;
import com.stackroute.model.Product;
import com.stackroute.model.User;
import com.stackroute.model.UserDto;
import com.stackroute.repository.ProductRepository;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository Repository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Product addProduct(Product product) throws SellerEmailInvalidException {
//		product.setProductId(product.getProductId());
//		product.setProductName(product.getProductName());
//		product.setSellerEmail(product.getSellerEmail());
//		product.setProductPrice(product.getProductPrice());
//		product.setDescription(product.getDescription());
		Product savedProduct = null;
		if(userRepository.existsBySellerEmail(product.getSellerEmail())){
			savedProduct = Repository.save(product);
			return savedProduct;
		}
		throw new SellerEmailInvalidException(product.getSellerEmail());
	}

	@Override

	public Object addProductImage(String productId, MultipartFile multipartFile) {
		boolean x = Repository.existsByProductId(productId);
		Product productfromRepo = null;
		if (!x) {
			try {
				throw new ProductNotFoundException("product not found");
			} catch (ProductNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			// productfromRepo = productRepository.findByProductName(productName);
			productfromRepo = Repository.findById(productId).get();
			try {
				log.info("In Service Class for Add Method for adding Image");
				productfromRepo.setProductImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
				Repository.save(productfromRepo);
				log.info("In Service Class for Add Method for adding Image sucessfull");

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		return productfromRepo;
	}

//		public ProductSell saveProduct(ProductSell productSell,MultipartFile file) throws IOException {
//		productSell.setProductImage(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
//		productSell.setProductName(productSell.getProductName());
//		productSell.setProductPrice(productSell.getProductPrice());
//		productSell.setDescription(productSell.getDescription());
//		productSell.setSellerEmail(productSell.getSellerEmail());
//		return productRepository.save(productSell);
//	}
	@Override
	public List<Product> getAllProduct() {
		return Repository.findAll();
	}

	@Override
	public Product getProductById(String productId) {
		Optional<Product> productFromRepo = Repository.findById(productId);
		if (productFromRepo.isPresent()) {
			return productFromRepo.get();
		} else {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
	}

	@Override
	public Product getProductBySellerEmail(String sellerEmail) {
		return Repository.findBySellerEmail(sellerEmail);
	}

	@Override
	public String deleteProductById(String productId) {
		if (!Repository.existsById(productId)) {
			throw new IdNotFoundException("SERVICE.PRODUCT_NOT_FOUND");
		}
		Repository.deleteById(productId);
		return "Product deleted successfully";
	}

	@Override
	public Product updateProductById(Product product) throws SellerEmailInvalidException {
		Optional<Product> productDb = Repository.findById(product.getProductId());
		if (productDb.isPresent()) {
			Product productFromRepo = productDb.get();
			productFromRepo.setProductName(product.getProductName());
			productFromRepo.setSellerEmail(product.getSellerEmail());
			productFromRepo.setProductPrice(product.getProductPrice());
			productFromRepo.setProductCategory(product.getProductCategory());
			productFromRepo.setProductDescription(product.getProductDescription());
			productFromRepo.setProductRatings(product.getProductRatings());
			productFromRepo.setBrand(product.getBrand());
			productFromRepo.setInventoryAmount(product.getInventoryAmount());

			return Repository.save(productFromRepo);
		}
		throw new SellerEmailInvalidException(product.getSellerEmail());

	}

	public void save(UserDto user) {
		User existingUser = new User();
		existingUser.setSellerId(user.getSellerId());
		existingUser.setSellerName(user.getSellerName());
		existingUser.setSellerEmail(user.getSellerEmail());
		existingUser.setSellerPassword(user.getSellerPassword());
		existingUser.setSellerAddress(user.getSellerAddress());
		existingUser.setSellerPincode(user.getSellerPincode());
		userRepository.save(existingUser);
	}


}