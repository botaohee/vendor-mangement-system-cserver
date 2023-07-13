package com.info5059.casestudy.order;


import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import jdk.jfr.Percentage;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class POPDFGenerator extends AbstractPdfView{


    public static Cell generateVendorCell (String val)  throws IOException
    {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        return new Cell().add(new Paragraph(val)
                .setFont(font)
                .setFontSize(12)
                .setBold())
                .setTextAlignment(TextAlignment.LEFT)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setBorder(Border.NO_BORDER);
    };
    public static Cell generateProductCell (String val, int size,String pos, boolean bolded)  throws IOException
    {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        if(bolded == true)
        {
            return new Cell().add(new Paragraph(val)
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setWidth(new UnitValue(UnitValue.PERCENT, size))
                    .setTextAlignment(TextAlignment.CENTER);
        }
        else{
            if(pos == "center")
            {
                return new Cell().add(new Paragraph(val)
                        .setFont(font)
                        .setFontSize(12)
                )
                        .setWidth(new UnitValue(UnitValue.PERCENT, size))
                        .setTextAlignment(TextAlignment.CENTER);
            }
            else if (pos == "left")
            {
                return new Cell().add(new Paragraph(val)
                        .setFont(font)
                        .setFontSize(12)
                )
                        .setWidth(new UnitValue(UnitValue.PERCENT, size))
                        .setTextAlignment(TextAlignment.LEFT);
            }
            else
            {
                return new Cell().add(new Paragraph(val)
                        .setFont(font)
                        .setFontSize(12)
                )
                        .setWidth(new UnitValue(UnitValue.PERCENT, size))
                        .setTextAlignment(TextAlignment.RIGHT);
            }
        }





    };



    public static ByteArrayInputStream generateReport(String poid,
                                                      PurchaseOrderDAO repDAO,
                                                      VendorRepository vendorRepository,
                                                      ProductRepository productRepository) throws IOException {

        URL imageUrl = POPDFGenerator.class.getResource("/static/assets/logo.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
// Initialize PDF document to be written to a stream not a file
        PdfDocument pdf = new PdfDocument(writer);
// Document is the main object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
// add the image to the document
        Image img = new Image(ImageDataFactory.create(imageUrl))
                .scaleAbsolute(110, 110)
                .setFixedPosition(110, 680);
        document.add(img);
// now let's add a big heading
        document.add(new Paragraph("\n\n"));
        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);


        try {

            PurchaseOrder purchaseOrder = repDAO.findOne(Long.parseLong(poid));
            document.add(new Paragraph(String.format("Purchase Order"))
                    .setFont(font)
                    .setFontSize(24)
                    .setMarginRight(25)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold());
            document.add(new Paragraph("#:" + poid)
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginRight(90)
                    .setMarginTop(-10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));

            Table vendorTable = new Table(2);
            vendorTable.setWidth(new UnitValue(UnitValue.PERCENT, 35))
                    .setHorizontalAlignment(HorizontalAlignment.LEFT);

            Optional<Vendor> opt = vendorRepository.findById(purchaseOrder.getVendorid());

            Cell vendor9 = new Cell().add(new Paragraph("        ") // seven whitespaces
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER);
            Cell cell = new Cell().add(new Paragraph("Vendor :")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(Border.NO_BORDER);

            if (opt.isPresent()) {
                Vendor vendor = opt.get();
                vendorTable.addCell(cell);
                vendorTable.addCell(generateVendorCell(vendor.getName()));
                vendorTable.addCell(vendor9);
                vendorTable.addCell(generateVendorCell(vendor.getAddress1()));
                vendorTable.addCell(vendor9);
                vendorTable.addCell(generateVendorCell(vendor.getCity()));
                vendorTable.addCell(vendor9);
                vendorTable.addCell(generateVendorCell(vendor.getProvince()));
                vendorTable.addCell(vendor9);
                vendorTable.addCell(generateVendorCell(vendor.getEmail()));
            }
            // report employee
            document.add(vendorTable);
            document.add(new Paragraph("\n\n"));

            BigDecimal tot = new BigDecimal(0.0);
            Table poTable = new Table(5);
            poTable.setWidth(new UnitValue(UnitValue.PERCENT, 100)) .setHorizontalAlignment(HorizontalAlignment.CENTER);;

            poTable.addCell(generateProductCell("Product Code",15,"center",true));
            poTable.addCell(generateProductCell("Description",30,"center",true));
            poTable.addCell(generateProductCell("Qty Sold",15,"center",true));
            poTable.addCell(generateProductCell("Price",15,"center",true));
            poTable.addCell(generateProductCell("Ext. Price",15,"center",true));


            for (PurchaseOrderLineitem line : purchaseOrder.getItems()) {
                Optional<Product> optx = productRepository.findById(line.getProductid());

                if ( optx.isPresent() ) {
                    Product product = optx.get();
                    tot = tot.add(line.getPrice().multiply(new BigDecimal(line.getQty())), new MathContext(8, RoundingMode.UP));

                    poTable.addCell(generateProductCell(product.getId(),15,"center",false));
                    poTable.addCell(generateProductCell(product.getName(),30,"center",false));
                    poTable.addCell(generateProductCell(String.valueOf(line.getQty()),15,"right",false));
                    poTable.addCell(generateProductCell(formatter.format(line.getPrice()),12,"right",false));
                    poTable.addCell(generateProductCell(formatter.format( line.getPrice().multiply(new BigDecimal(line.getQty()))),15,"right",false));

                }

            }




            // report total
            cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            poTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(tot)))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT)
                   ;
            poTable.addCell(cell);

            cell = new Cell(1, 4).add(new Paragraph("Tax:"))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            poTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(0.13)))))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT)
                    ;
            poTable.addCell(cell);


            cell = new Cell(1, 4).add(new Paragraph("PO Total:"))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            poTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(tot.multiply(new BigDecimal(1.13)))))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT)
                    .setBackgroundColor(ColorConstants.YELLOW);
            poTable.addCell(cell);


            document.add(poTable);

            document.add(new Paragraph("\n\n"));
            /*document.add(new Paragraph(String.valueOf(new Date()))
                    .setTextAlignment(TextAlignment.CENTER));
            */
            document.add(new Paragraph(ESTConverter(purchaseOrder.getPodate()))
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
        } catch (Exception ex) {
            Logger.getLogger(POPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }


    private static String ESTConverter(Date d)
    {
        //System.err.println(new Date());
        String tzid = "EST";
        TimeZone tz = TimeZone.getTimeZone(tzid);

        long utc = System.currentTimeMillis();  // supply your timestamp here
        // Date d = new Date(utc);

        // timezone symbol (z) included in the format pattern for debug
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // format date in default timezone
        //System.err.println(format.format(d));

        // format date in target timezone
        format.setTimeZone(tz);
        //System.err.println(format.format(d));
        return format.format(d);
    }

}
