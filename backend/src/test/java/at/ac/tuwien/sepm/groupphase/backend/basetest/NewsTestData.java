package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface NewsTestData {

    String BASE_URI = "/api/v1";
    String NEWS_BASE_URI = BASE_URI + "/news";
    String POST_REQUEST_NEW = "{\"entry\":\"Chancellor Palpatine is on his way ...\",\"title\":\"StarWars\",\"shortDescription\":\"Me sa thinks\"}";

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
