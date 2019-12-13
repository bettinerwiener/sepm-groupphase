package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface OrderTestData {

    String BASE_URI = "/api/v1";
    String ROOM_BASE_URI = BASE_URI + "/orders";

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
}
