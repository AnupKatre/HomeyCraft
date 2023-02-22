package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Payment")
public class PaymentDto {

    private String orderId;
    private String customerEmail;
    private String amount;
    private String receipt;
    private String status;
    private String razorpaySignature;
    private String paymentId;

}
