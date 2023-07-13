package com.info5059.casestudy.order;

import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.vendor.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@CrossOrigin
@RestController
public class POPDFController {

    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/POPDF", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> streamPDF(HttpServletRequest request) throws IOException {
        // get formatted pdf as a stream
        String poid = request.getParameter("poid");
        ByteArrayInputStream bis = POPDFGenerator.generateReport(poid,purchaseOrderDAO,vendorRepository,productRepository);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=poreport.pdf");
        // dump stream to browser
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
