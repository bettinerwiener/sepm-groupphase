package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
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
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SimplePdfService implements PdfService {

    private TicketService ticketService;
    private CustomUserDetailService userDetailsService;

    public SimplePdfService(TicketService ticketService, CustomUserDetailService userDetailsService) {
        this.ticketService = ticketService;
        this.userDetailsService = userDetailsService;
    }

/**
    @Override
    public ByteArrayInputStream getTicketPdf(Long id, String email) throws NotFoundException {

        Ticket ticket = ticketService.findById(id);
        User user = userDetailsService.findApplicationUserByEmail(email);
        if(ticket.getCustomerOrder().getUserId() != user.getId()) {
            throw new NotFoundException("No Ticket with that id found that is owned by this user");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            PdfReader reader = new PdfReader("ticket_template.pdf");

            reader.removeUsageRights();
            PdfStamper stamper = new PdfStamper(reader, out);
            stamper.setFormFlattening(true);
            AcroFields form = stamper.getAcroFields();
            form.setField("title", ticket.getPerformance().getEvent().getTitle());
            form.setField("name", user.getFirstName() + " " + user.getLastName());

            form.setField("date", ticket.getPerformance().getDate().toLocalDate().toString());
            form.setField("location", ticket.getPerformance().getRoom().getName() + ", " + ticket.getPerformance().getRoom().getLocation().getName());
            form.setField("seat", ticket.getSeat().getNumber() + ticket.getSeat().getRow());
            form.setField("price", ticket.getPrice().toString());

            BarcodeQRCode qrCode = new BarcodeQRCode(ticket.getId().toString(), 110, 110, null);
            Image qrCodeImage = qrCode.getImage();
            qrCodeImage.setAbsolutePosition(445,640);


            stamper.getOverContent(1).addImage(qrCodeImage);


            stamper.close();


            reader.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return new ByteArrayInputStream(out.toByteArray());

    }
*/

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

            placeText(contentStream, ticket.getPerformance().getEvent().getTitle(), 73, 280, 24);
            placeText(contentStream, user.getFirstName() + " " + user.getLastName(), 73, 193, 15);
            placeText(contentStream,  Date.from( ticket.getPerformance().getDate().atZone(ZoneId.systemDefault()).toInstant()).toString(), 73, 121, 15);
            placeText(contentStream, ticket.getPerformance().getRoom().getLocation().getName()+ ", " + ticket.getPerformance().getRoom().getName(), 73, 55, 15);

            placeText(contentStream, ticket.getSeat().getSeatNumber() + ticket.getSeat().getRowLetter(), 440, 121, 15);
            placeText(contentStream, ticket.getPrice().toString() + "€", 440, 55, 15);


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

    public void placeText(PDPageContentStream contentStream, String text, int x, int y, int size) {
        try {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, size);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            System.out.println(e);
        }



    }
}
