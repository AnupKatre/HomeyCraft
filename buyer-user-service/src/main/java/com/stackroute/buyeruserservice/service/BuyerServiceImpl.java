package com.stackroute.buyeruserservice.service;

import com.stackroute.buyeruserservice.Exception.BuyerNotFoundException;
import com.stackroute.buyeruserservice.model.Buyer;
import com.stackroute.buyeruserservice.rabbitMQ.ProducerConfig;
import com.stackroute.buyeruserservice.repository.BuyerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    public BuyerRepository buyerRepository;

    @Autowired
    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer saveBuyer(Buyer buyer) {
        Buyer repoBuyer = buyerRepository.save(buyer);
        template.convertAndSend(ProducerConfig.FANOUT_EXCHANGE,"", buyer);
        return repoBuyer;
    }

    public List<Buyer> getAllBuyerDetails() {
        return buyerRepository.findAll();
    }

    @Override
    public ResponseEntity<Buyer> getByEmail(String email) throws BuyerNotFoundException {
        Optional<Buyer> orders = buyerRepository.findByBuyerEmail(email);
        if(orders.isPresent()){
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        } else {
            throw new BuyerNotFoundException("Buyer email " + email + " is not found.");
        }
    }


    public String deleteBuyer(Buyer buyer) {
        buyerRepository.delete(buyer);

        return "successfully deleted";
    }
}
