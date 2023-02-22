package com.stackroute.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Setter
@Getter
public class User {


    @Id
    @Size(min = 3, max = 50)
    private String userName;
    @NotBlank
    @Size(min = 6, max = 100)
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_NAME")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_NAME")
            }
    )
    private Set<Role> role;

}