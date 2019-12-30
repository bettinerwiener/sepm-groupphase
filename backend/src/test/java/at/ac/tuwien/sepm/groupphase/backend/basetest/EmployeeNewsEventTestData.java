package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeNewsEventTestData {

    String BASE_URI = "/api/v1";
    String EVENT_NEWS_BASE_URI = BASE_URI + "/eventnews";

    String POST_EVENT_NEWS_JSON = "";

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
