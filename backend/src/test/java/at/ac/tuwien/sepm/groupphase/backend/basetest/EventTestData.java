package at.ac.tuwien.sepm.groupphase.backend.basetest;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface EventTestData {

    String BASE_URI = "/api/v1";
    String EVENT_BASE_URI = BASE_URI + "/events";
    String EVENT_ID_BASE_URI = BASE_URI + "/events/1";
    String EVENT_BASE_URI_TOP_TEN = "/api/v1/events/topten";
    String EVENT_FILTER_URI = "/api/v1/events?duration=2.5&searchTerm=trek&startDate=2019-12-10&location=2";

    String NEW_EVENT = "{\"contents\":\"Chancellor Palpatine is on his way ...\"," +
        "\"title\":\"StarWars\",\"shortDescription\":\"Me sa thinks\", \"duration\":\"2.5\", \"category\":\"FILM\"}";

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
