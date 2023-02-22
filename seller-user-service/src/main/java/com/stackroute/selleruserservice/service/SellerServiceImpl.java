package com.stackroute.selleruserservice.service;

import com.stackroute.selleruserservice.Exception.SellerNotFoundException;
import com.stackroute.selleruserservice.model.Seller;
import com.stackroute.selleruserservice.rabbitMQ.ProducerConfig;
import com.stackroute.selleruserservice.repository.SellerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    public SellerRepository sellerRepository;
    @Autowired
    private RabbitTemplate template;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository){
        this.sellerRepository=sellerRepository;
    }

    public Seller saveSeller(Seller seller){
        Seller repoSeller = sellerRepository.save(seller);
        template.convertAndSend(ProducerConfig.FANOUT_EXCHANGE,"", repoSeller);
        return repoSeller;
    }

    public List<Seller> getAllSellerDetails(){
        return sellerRepository.findAll();
    }

	@Override
	public String deleteSeller(Seller seller) {
		sellerRepository.delete(seller);
		return "Deleted";
	}
    @Override
    public ResponseEntity<Seller> getByEmail(String email) throws SellerNotFoundException {
        Optional<Seller> orders = sellerRepository.findBySellerEmail(email);
        if(orders.isPresent()){
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        } else {
            throw new SellerNotFoundException("Seller email " + email + " is not found.");
        }
    }

}
