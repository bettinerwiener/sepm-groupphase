package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotSavedException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import at.ac.tuwien.sepm.groupphase.backend.service.OrderService;
import at.ac.tuwien.sepm.groupphase.backend.service.PdfService;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.AccessControlException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SimplePdfService implements PdfService {

    private TicketService ticketService;
    private CustomUserDetailService userDetailsService;
    private OrderService orderService;

    public SimplePdfService(TicketService ticketService, CustomUserDetailService userDetailsService, OrderService orderService) {
        this.ticketService = ticketService;
        this.userDetailsService = userDetailsService;
        this.orderService = orderService;
    }

    @Override
    public ByteArrayInputStream getTicketPdf(Long id, String email) throws NotFoundException {

        Ticket ticket = ticketService.findById(id);
        User user = userDetailsService.findApplicationUserByEmail(email);
        if(ticket.getCustomerOrder().getUserId() != user.getId()) {
            throw new NotFoundException("No Ticket with that id found that is owned by this user");
        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(800, 350));
        doc.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            PDImageXObject bg = PDImageXObject.createFromFile("src/main/resources/bg_ticket.png", doc);
            contentStream.drawImage(bg, 0, 0);

            placeText2(contentStream,ticket.getPerformance().getEvent().getTitle(), 73, 280, 24);
            placeText2(contentStream, user.getFirstName() + " " + user.getLastName(), 73, 193, 15);
            placeText2(contentStream,  Date.from( ticket.getPerformance().getDate().atZone(ZoneId.systemDefault()).toInstant()).toString(), 73, 121, 15);
            placeText2(contentStream, ticket.getPerformance().getRoom().getLocation().getName()+ ", " + ticket.getPerformance().getRoom().getName(), 73, 55, 15);
            placeText2(contentStream, ticket.getSeat().getSeatNumber() + ticket.getSeat().getRowLetter(), 440, 121, 15);
            placeText2(contentStream, ticket.getPrice().toString() + "€", 440, 55, 15);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(ticket.getId().toString(),BarcodeFormat.QR_CODE, 140, 140, null);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);

            PDImageXObject qr = PDImageXObject.createFromByteArray(doc, out.toByteArray(), "qr");
            contentStream.drawImage(qr, 600, 85);

            contentStream.close();

        } catch (Exception e) {
            log.error("Ticket pdf creation failed: {}", e.getMessage());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            doc.save(out);
        } catch (Exception e) {
            log.error("Ticket pdf could not be saved: {}", e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }


    @Override
    public ByteArrayInputStream getOrderInvoicePdf(Long id, String email) throws NotFoundException {

        Order order = orderService.findById(id);
        List<Ticket> tickets = ticketService.findTicketsByOrderId(order.getId());
        User user = userDetailsService.findApplicationUserByEmail(email);

        String companyName = "Ticketline GmbH";
        String companyCity = "Wien 1230";
        String companyStreet = "Breitenfurterstraße 380C/5/8";
        String customerString = user.getFirstName() + " " + user.getLastName();
        //Temporary
        String date = "22.01.2019";
        Float totalPrice = 0f;

        System.out.println(order.getId());
        if(order.getUserId() != user.getId()) {
            throw new NotFoundException("No Ticket with that id found that is owned by this user");
        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        //ADD CHECK IF TICKET STATUS IS BOUGHT!
        //ZEILENUMBRUCH BEI ZU BREITEM TITEL USW.?
        try {

            int counter = 0;
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            placeText(contentStream,page, "Rechnung #" + order.getId(),  75, 80, 17);
            placeText(contentStream,page, user.getFirstName() + " " + user.getLastName(),  75, 100, 14);


            placeText(contentStream,page, companyName, 410, 80 + 15*2, 11);
            placeText(contentStream,page, companyStreet, 410, 80 + 15*1, 11);
            placeText(contentStream,page, companyCity, 410, 80, 11);

            for(Ticket ticket: tickets) {

                EventPerformance performance = ticket.getPerformance();
                Event event = performance.getEvent();
                Seat seat = ticket.getSeat();
                Float price = ticket.getPrice();
                totalPrice += price;

                placeText(contentStream,page, "Menge", 75, 180, 11);
                placeText(contentStream,page, "Bezeichnung", 135, 180, 11);
                placeText(contentStream,page, "Preis", 470, 180, 11);


                placeText(contentStream,page, "1", 75, 210 + counter*20, 11);

                placeText(contentStream,page, event.getTitle() + " - " + performance.getRoom().getLocation().getName() + ", " + performance.getRoom().getName() + "(" + seat.getSection().getLetter() + "," + seat.getRowLetter() + seat.getSeatNumber() + ")", 135, 210 + counter*20, 11);

                placeText(contentStream,page, ticket.getPrice().toString()+"€", 470 ,210 + counter*20, 11);

                counter++;
            }

            placeText(contentStream,page, "Summe: " + totalPrice + "€", 400, 220 + counter*20, 13);




            contentStream.close();

        } catch (Exception e) {
            log.error("Ticket pdf creation failed: {}", e.getMessage());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            doc.save(out);
        } catch (Exception e) {
            log.error("Ticket pdf could not be saved: {}", e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

    public void placeText2(PDPageContentStream contentStream, String text, int x, int y, int size) {
        try {
            System.out.println();


            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, size);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void placeText(PDPageContentStream contentStream, PDPage page, String text, int x, int y, int size) {
        try {
            System.out.println();


            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, size);
            contentStream.newLineAtOffset(x, page.getTrimBox().getHeight() - y);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
