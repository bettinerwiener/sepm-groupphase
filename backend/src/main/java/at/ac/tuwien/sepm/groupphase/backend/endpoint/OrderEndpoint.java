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
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "/api/v1/orders")
@Slf4j
public class OrderEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OrderService orderService;
    private final CustomUserDetailService userDetailService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final TicketMapper ticketMapper;
    private final TicketService ticketService;
    private final PdfService pdfService;

    public OrderEndpoint(PdfService pdfService, OrderService orderService, OrderMapper orderMapper, CustomUserDetailService userDetailService, TicketMapper ticketMapper, ShoppingCartService shoppingCartService, TicketService ticketService) {
        this.pdfService = pdfService;
        this.userDetailService = userDetailService;
        this.orderService = orderService;
        this.shoppingCartService =shoppingCartService;
        this.orderMapper = orderMapper;
        this.ticketMapper = ticketMapper;
        this.ticketService = ticketService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Get all orders", authorizations = {@Authorization(value = "apiKey")})
    public List<OrderDto> getAllByUser(Authentication authentication) {
        try {
            User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
            List<OrderDto> orderDtos = orderService.findByUserId(user.getId()).stream().
                map(order -> {
                    List<Ticket> tickets  = ticketService.findTicketsByOrderId(order.getId());
                    OrderDto orderDto = orderMapper.orderToOrderDto(order);
                    orderDto.setTickets(tickets);
                    return orderDto;
                }).collect(Collectors.toList());
            return orderDtos;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @CrossOrigin
    @PostMapping(value= "/buy")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "make new order", authorizations = {@Authorization(value = "apiKey")})
    public OrderDto newOrder(Authentication authentication,@RequestBody List<TicketDto> ticketDtos) {
        System.out.println("Got here");
        System.out.println(ticketDtos);
        User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
        List<Ticket> tickets = ticketMapper.ticketDtoToTicket(ticketDtos);
        return orderMapper.orderToOrderDto(shoppingCartService.BuyTickets(user,tickets));
    }

    @CrossOrigin
    @PostMapping(value= "/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "make new reservation", authorizations = {@Authorization(value = "apiKey")})
    public OrderDto newReservation(Authentication authentication, @RequestBody List<TicketDto> ticketDtos) {
        User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
        List<Ticket> tickets = ticketMapper.ticketDtoToTicket(ticketDtos);
        return orderMapper.orderToOrderDto(shoppingCartService.ReserveTickets(user,tickets));
    }

    @CrossOrigin
    @PutMapping(value= "/cancel")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "cancel tickets", authorizations = {@Authorization(value = "apiKey")})
    public List<TicketDto> cancelTickets(Authentication authentication, @RequestBody List<TicketDto> ticketDtos) {
        User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
        List<Ticket> tickets = ticketMapper.ticketDtoToTicket(ticketDtos);
        return ticketMapper.ticketToTicketDto(shoppingCartService.CancelTickets(user, tickets));
    }

    @CrossOrigin
    @PutMapping(value= "/cancelpurchase", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "cancel tickets", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<InputStreamSource> cancelPurchase(Authentication authentication, @RequestBody List<TicketDto> ticketDtos) {
        User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());

        List<Ticket> tickets = ticketMapper.ticketDtoToTicket(ticketDtos);
        ByteArrayInputStream bis = null;



        bis = pdfService.getCancelInvoicePdf(tickets, authentication.getPrincipal().toString());


        shoppingCartService.CancelTickets(user, tickets);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice.pdf");

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
}


