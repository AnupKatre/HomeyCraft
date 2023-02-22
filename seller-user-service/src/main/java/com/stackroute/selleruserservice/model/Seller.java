package com.stackroute.selleruserservice.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sellers")
public class Seller {

    @Id
    private String sellerId=UUID.randomUUID().toString();
    private String sellerName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerAddress;
    private String sellerPincode;

}