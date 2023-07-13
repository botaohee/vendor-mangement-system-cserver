package com.info5059.casestudy.order;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data
@RequiredArgsConstructor
public class PurchaseOrderLineitem {
    // PurchaseOrderLineitem private members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long poid;
    private String productid;
    private int qty;
    private BigDecimal price;
}
