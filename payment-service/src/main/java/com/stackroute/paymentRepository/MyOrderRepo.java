package com.stackroute.paymentRepository;

import com.stackroute.paymentEntity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyOrderRepo extends JpaRepository<MyOrder,Long> {

    public MyOrder findByOrderId(String orderId);
    public Optional<MyOrder> findByCustomerEmail(String customerEmail);
}
