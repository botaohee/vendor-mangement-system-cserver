package com.info5059.casestudy.order;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
public class PurchaseOrder {

    // PurchaseOrder private members
    @Id
    @GeneratedValue
    private Long Id;
    private Long vendorid;
    private BigDecimal amount;
    @OneToMany(mappedBy = "poid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderLineitem> items = new ArrayList<PurchaseOrderLineitem>();
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
    private Date podate;

}
