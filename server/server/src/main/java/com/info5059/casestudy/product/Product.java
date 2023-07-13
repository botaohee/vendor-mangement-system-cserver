package com.info5059.casestudy.product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
/**
 * Expense entity
 */
@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
/*    • costprice – is what we purchase the good for, make sure it is lower than MSRP which is what we sell it for
      • rop – is the Reorder Point, when stock falls to this # we re-order the good
      • eoq – is the Economic Order Quantity this is a value calculated by some bean counter who has figured out that it makes most economic sense to order this amount. This value will be smaller for say a fridge than it would for a good like a thumb tack
      • qoh – Quantity on Hand, what we have in inventory
      • qoo – Quantity on Order. what we have ordered but not received
      • qrcode – we’ll use this in case 2
      • qrcodetxt – we’ll use this case 2*/

    private String Id;
    private int vendorid; // FK

    private String name;

    private BigDecimal costprice;;
    private BigDecimal msrp;

    private int rop;
    private int eoq;
    private int qoh;
    private int qoo;

    @Basic(optional = true)
    @Lob

    // Right now, it’s just a string. If we leave it like this, it will only be able to store 255 bytes which is not large enough to house our qrcodes

    private byte[] qrcode;
    private String qrcodetxt;
}
