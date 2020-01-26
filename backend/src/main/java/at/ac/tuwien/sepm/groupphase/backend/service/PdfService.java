package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PdfService {


    /**
     * Produces a ticket in pdf for ticket id and a user
     * @param id of ticket the pdf is produced for
     * @param email of the user the ticket is created for
     * @return a ByteArrayInpuStream holding the binary pdf ticket
     * @throws NotFoundException
     */
    ByteArrayInputStream getTicketPdf(Long id, String email) throws NotFoundException;

    /**
     * Produces an invoice in pdf for the specified order id
     * @param id of the order
     * @param email of the user
     * @return a ByteArrayInpuStream holding the binary pdf invoice
     * @throws NotFoundException
     */
    ByteArrayInputStream getOrderInvoicePdf(Long id, String email) throws NotFoundException;

    /**
     * Produces a cancel invoice in pdf for the specified order id
     * @param tickets list of the tickets to generate the invoice for
     * @param email of the user
     * @return a ByteArrayInpuStream holding the binary pdf cancel invoice
     * @throws NotFoundException
     */
    ByteArrayInputStream getCancelInvoicePdf(List<Ticket> tickets, String email) throws NotFoundException;

}
