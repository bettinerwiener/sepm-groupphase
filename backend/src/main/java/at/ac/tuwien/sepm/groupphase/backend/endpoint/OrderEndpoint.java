package at.ac.tuwien.sepm.groupphase.backend.endpoint;


import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.OrderDto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.OrderMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;



import at.ac.tuwien.sepm.groupphase.backend.entity.User;

import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import at.ac.tuwien.sepm.groupphase.backend.service.OrderService;

import at.ac.tuwien.sepm.groupphase.backend.service.ShoppingCartService;


import at.ac.tuwien.sepm.groupphase.backend.service.impl.CustomUserDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orders")

public class OrderEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OrderService orderService;
    private final CustomUserDetailService userDetailService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final TicketMapper ticketMapper;


    public OrderEndpoint(OrderService orderService, OrderMapper orderMapper, CustomUserDetailService userDetailService, TicketMapper ticketMapper, ShoppingCartService shoppingCartService) {
        this.userDetailService = userDetailService;
        this.orderService = orderService;
        this.shoppingCartService =shoppingCartService;
        this.orderMapper = orderMapper;
        this.ticketMapper = ticketMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Get all events", authorizations = {@Authorization(value = "apiKey")})
    public List<OrderDto> getAllByUser(Authentication authentication) {
        try {
            User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
            System.out.println(user.getId());
            List<OrderDto> orderDtos = orderService.findByUserId(user.getId()).stream().
                map(order -> orderMapper.orderToOrderDto(order)).collect(Collectors.toList());
            return orderDtos;

        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @CrossOrigin
    @PostMapping(value= "/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "make new order", authorizations = {@Authorization(value = "apiKey")})
    public OrderDto newOrder(Authentication authentication,@RequestBody List<TicketDto> ticketDtos) {
        User user = userDetailService.findApplicationUserByEmail(authentication.getPrincipal().toString());
        List<Ticket> tickets = ticketMapper.ticketDtoToTicket(ticketDtos);
        return orderMapper.orderToOrderDto(shoppingCartService.BuyTickets(user,tickets));
    }




}


