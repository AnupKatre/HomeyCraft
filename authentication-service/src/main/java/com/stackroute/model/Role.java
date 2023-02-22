package com.stackroute.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Role {

    @Id
    private String roleName;
    @Column(length = 60)
    private String roleDescription;

}
