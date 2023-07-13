package com.info5059.casestudy.order;

import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.vendor.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@CrossOrigin
@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;


    @PostMapping("/api/pos")
    public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder clientord) { // use RequestBody here
        Long purchaseId = purchaseOrderDAO.create(clientord);
        return new ResponseEntity<Long>(purchaseId, HttpStatus.OK);
    }

    @GetMapping("/api/pos/{id}")
    public ResponseEntity< Iterable<PurchaseOrder>> findReportByVendorId(@PathVariable long id) { // use RequestBody here
        Iterable<PurchaseOrder> poId = purchaseOrderDAO.findByVendor(id);
        return new ResponseEntity< Iterable<PurchaseOrder>>(poId, HttpStatus.OK);
    }


}
