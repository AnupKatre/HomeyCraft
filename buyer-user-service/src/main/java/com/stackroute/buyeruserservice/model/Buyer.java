package com.stackroute.buyeruserservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buyers")
public class Buyer {
    @Id
    private String buyerId = UUID.randomUUID().toString();
    private String buyerName;
    private String buyerEmail;
    private String buyerPassword;
    private String buyerAddress;
    private String buyerPincode;

}