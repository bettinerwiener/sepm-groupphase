package at.ac.tuwien.sepm.groupphase.backend.integrationtest;
import at.ac.tuwien.sepm.groupphase.backend.basetest.OrderTestData;
import at.ac.tuwien.sepm.groupphase.backend.config.properties.SecurityProperties;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SimpleMessageDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.security.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Arrays;
import java.util.List;

import static at.ac.tuwien.sepm.groupphase.backend.basetest.TestData.ADMIN_ROLES;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderEndpointTest implements OrderTestData {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    PlatformTransactionManager txm;


    TransactionStatus txstatus;

    @BeforeEach
    public void setupDBTransaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        txstatus = txm.getTransaction(def);
        assumeTrue(txstatus.isNewTransaction());
        txstatus.setRollbackOnly();
    }

    @AfterEach
    public void tearDownDBData() {
        txm.rollback(txstatus);
    }
    /**
     * Test Reservation
     */
    @Test
    public void creatingNewReserveOrderReturn201() throws Exception {
        this.mockMvc.perform(post(ORDER_RESERVE_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(RESERVE_TWO_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

    }

   @Test
    public void creatingNewReserveOrderWithReservedByUserAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_RESERVE_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(RESERVE_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    @Test
    public void creatingNewReserveOrderWithReservedAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_RESERVE_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(RESERVE_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }
    @Test
    public void creatingNewReserveOrderWithBoughtAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_RESERVE_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(RESERVE_ONE_BOUGHT_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }
    /**
     * Test Buying
     */
    @Test
    public void creatingNewBuyOrderReturn201() throws Exception {
        this.mockMvc.perform(post(ORDER_BUY_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_TWO_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void creatingNewBuyOrderWithReservedByUserAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUY_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    @Test
    public void creatingNewBuyOrderWithReservedAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUY_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }
    @Test
    public void creatingNewBuyOrderWithBoughtAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUY_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_BOUGHT_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    /**
     * Test Buying of reserved
     */
    @Test
    public void buyTwoReservedReturn201() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_TWO_RESERVED_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void buyReservedWithTwoAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_TWO_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    @Test
    public void buyReservedWithReservedByUserAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    @Test
    public void buyReservedWithReservedAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }
    @Test
    public void buyReservedWithBoughtAndAvailableReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_ONE_BOUGHT_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }

    @Test
    public void buyReservedWithTwoReservedFromDifferentOrdersReturn423() throws Exception {
        this.mockMvc.perform(post(ORDER_BUYRESERVED_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(BUY_TWO_RESERVED_IN_DIFFERENT_ORDERS_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isLocked());
    }



    /**
     * Test Cancellation
     */

    @Test
    public void cancelTwoAvailableTicketsReturn422() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_TWO_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void cancelTwoBoughtTicketsReturn200() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_TWO_BOUGHT_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void cancelTwoReservedTicketsReturn200() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_TWO_RESERVED_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void cancelOneBoughtOneAvailableTicketsReturn422() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_ONE_BOUGHT_ONE_AVAILABLE_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void cancelTwoBoughtByAnotherUserTicketsReturn422() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_TWO_BOUGHT_BY_ANOTHER_USER_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void cancelTwoBoughtButLessThan14DaysToEventTicketsReturn422() throws Exception {
        this.mockMvc.perform(put(ORDER_CANCEL_URI)
            .header(securityProperties.getAuthHeader(), jwtTokenizer.getAuthToken(DEFAULT_USER, USER_ROLES))
            .contentType(MediaType.APPLICATION_JSON)
            .content(CANCEL_TWO_BOUGHT_BUT_LESS_THAN_14DAYS_TO_EVENT_TICKETS)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());
    }

}
