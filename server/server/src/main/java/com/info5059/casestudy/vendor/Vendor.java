package com.info5059.casestudy.vendor;

import com.info5059.casestudy.product.Product;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor


public class Vendor {
    @OneToMany(mappedBy = "vendorid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String address1;
    private String city;
    private String province;
    private String postalcode;
    private String phone;
    private String type;
    private String email;
}
