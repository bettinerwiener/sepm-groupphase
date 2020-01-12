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

    ByteArrayInputStream getOrderInvoicePdf(Long id, String email) throws NotFoundException;

}
