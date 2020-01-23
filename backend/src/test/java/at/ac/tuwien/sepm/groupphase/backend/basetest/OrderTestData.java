package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface OrderTestData {

    String BASE_URI = "/api/v1";
    String ORDER_BASE_URI = BASE_URI + "/orders";
    String ORDER_RESERVE_URI = BASE_URI + "/orders/reserve";
    String ORDER_BUY_URI = BASE_URI + "/orders/buy";
    String ORDER_CANCEL_URI = BASE_URI + "/orders/cancel";

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


    String BUY_TWO_AVAILABLE_TICKETS = "[ { \"id\":\"2\" }, { \"id\":\"8\" }]";
    String BUY_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"6\" }, { \"id\":\"8\" }]";
    String BUY_ONE_BOUGHT_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"3\" }, { \"id\":\"8\" }]";
    String BUY_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"669\" }, { \"id\":\"8\" }]";

    String RESERVE_TWO_AVAILABLE_TICKETS = "[ { \"id\":\"2\" }, { \"id\":\"8\" }]";
    String RESERVE_ONE_RESERVED_BY_OTHER_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"6\" }, { \"id\":\"8\" }]";
    String RESERVE_ONE_BOUGHT_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"3\" }, { \"id\":\"8\" }]";
    String RESERVE_ONE_RESERVED_BY_USER_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"669\" }, { \"id\":\"8\" }]";


    String CANCEL_TWO_AVAILABLE_TICKETS = "[ { \"id\":\"2\" }, { \"id\":\"8\" }]";
    String CANCEL_TWO_BOUGHT_TICKETS = "[ { \"id\":\"303\" }, { \"id\":\"603\" }]";
    String CANCEL_TWO_RESERVED_TICKETS = "[ { \"id\":\"69\" }, { \"id\":\"369\" }]";
    String CANCEL_ONE_BOUGHT_ONE_AVAILABLE_TICKETS = "[ { \"id\":\"303\" }, { \"id\":\"2\" }]";
    String CANCEL_TWO_BOUGHT_BY_ANOTHER_USER_TICKETS = "[ { \"id\":\"4\" }, { \"id\":\"7\" }]";
    String CANCEL_TWO_BOUGHT_BUT_LESS_THAN_14DAYS_TO_EVENT_TICKETS = "[ { \"id\":\"3\" }, { \"id\":\"903\" }]";
}
