package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.OrderDto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.OrderMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;



import at.ac.tuwien.sepm.groupphase.backend.entity.User;

import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import at.ac.tuwien.sepm.groupphase.backend.service.OrderService;

import at.ac.tuwien.sepm.groupphase.backend.service.PdfService;
import at.ac.tuwien.sepm.groupphase.backend.service.ShoppingCartService;


import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import at.ac.tuwien.sepm.groupphase.backend.service.impl.CustomUserDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/pdf")
public class PdfEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CustomUserDetailService userDetailService;
    private final PdfService pdfService;
    private final TicketService ticketService;

    public PdfEndpoint(TicketService ticketService, PdfService pdfService, OrderMapper orderMapper, CustomUserDetailService userDetailService, TicketMapper ticketMapper, ShoppingCartService shoppingCartService) {
        this.userDetailService = userDetailService;
        this.pdfService = pdfService;
        this.ticketService = ticketService;
    }


    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(value = "Get ticket pdf", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<InputStreamSource> getTicketPdf(Authentication authentication, @PathVariable Long id) {

        System.out.println(id);

        ByteArrayInputStream bis = pdfService.getTicketPdf(id, authentication.getPrincipal().toString());

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket.pdf");

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));

    }


    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(value = "Get ticket pdf", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<InputStreamSource> getOrderInvoicePdf(Authentication authentication, @PathVariable Long id) {

        System.out.println(id);
        ByteArrayInputStream bis = pdfService.getOrderInvoicePdf(id, authentication.getPrincipal().toString());

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket.pdf");

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));

    }


}


