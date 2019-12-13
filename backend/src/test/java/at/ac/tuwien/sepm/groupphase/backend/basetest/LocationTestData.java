package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface LocationTestData {

    String BASE_URI = "/api/v1";
    String LOCATION_BASE_URI = BASE_URI + "/locations";

    String POST_REQUEST_NEW = "{\"name\":\"Staatsoper\",\"street\":\"Kaertner Ring 12\",\"city\":\"Wien\",\"postalCode\":1010}";
    String POST_REQUEST_ALREADY_THERE = "{\"name\":\"Metropol\",\"street\":\"Hernalser Hauptstrasse 12\",\"city\":\"Wien\",\"postalCode\":1170}";

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
