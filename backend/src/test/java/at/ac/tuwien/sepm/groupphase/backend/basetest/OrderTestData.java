package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface OrderTestData {

    String BASE_URI = "/api/v1";
    String ORDER_BASE_URI = BASE_URI + "/orders";
    String ORDER_RESERVE_URI = BASE_URI + "/orders/reserve";
    String ORDER_BUY_URI = BASE_URI + "/orders/buy";

    String ADMIN_USER = "hugo1@gmail.com";
    List<String> ADMIN_ROLES = new ArrayList<>() {
        {
            add("ROLE_ADMIN");
            add("ROLE_USER");
        }
    };
    String DEFAULT_USER = "hugo@gmail.com";
    List<String> USER_ROLES = new ArrayList<>() {
        {
            add("ROLE_USER");
        }
    };


    String BUY_TWO_AVAILABLE_TICKETS = "[ { \"id\":\"7\" }, { \"id\":\"4\" }]";
    String BUY_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"3\" }, { \"id\":\"7\" }]";
    String BUY_ONE_BOUGHT_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"2\" }, { \"id\":\"7\" }]";
    String BUY_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"5\" }, { \"id\":\"7\" }]";

    String RESERVE_TWO_AVAILABLE_TICKETS = "[ { \"id\":\"8\" }, { \"id\":\"9\" }]";
    String RESERVE_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"3\" }, { \"id\":\"7\" }]";
    String RESERVE_ONE_BOUGHT_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"2\" }, { \"id\":\"7\" }]";
    String RESERVE_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"5\" }, { \"id\":\"7\" }]";



}
