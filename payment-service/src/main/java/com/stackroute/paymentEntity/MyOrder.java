package com.stackroute.paymentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class MyOrder {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String orderId;
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private String orderId= UUID.randomUUID().toString();
   private String customerEmail;
    private String amount;
    private String receipt;
    private String status;
    private String razorpaySignature;
    @GeneratedValue(generator="system-uuid")
    private String paymentId;

}
