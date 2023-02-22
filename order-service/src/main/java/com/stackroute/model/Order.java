package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(collection = "Order")
public class Order {

    @Transient
    public static final String SEQUENCE_NAME ="user_sequence";

    @Id
    private String id = UUID.randomUUID().toString();
    private String buyerEmail;
    private String paymentStatus;
    private String orderStatus;
    private Map<String, ProductDto> Products;
    private Map<String,Integer> productQuantity;
    private int totalItems;
    private float totalOrderValue;


}
