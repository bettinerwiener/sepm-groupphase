package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeNewsEventTestData {

    String BASE_URI = "/api/v1";
    String EVENT_NEWS_BASE_URI = BASE_URI + "/eventnews";

    String POST_EVENT_NEWS_JSON = "{\"event\":{\"id\":1,\"title\":\"Star Wars\",\"shortDescription\":\"In a galaxy far far ...\",\"contents\":\"The Star Wars universe ...\",\"category\":\"FILM\",\"duration\":2.5,\"employee\":{\"id\":1,\"firstName\":\"Hugo\",\"lastName\":\"Deval\",\"email\":\"hugo@gmail.com\",\"password\":\"$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq\",\"locked\":false,\"isEmployee\":false,\"loginCount\":0}}," +
        "\"news\":{\"id\":null,\"entry\":\"That is not fair\",\"title\":\"Star Wars IX\",\"shortDescription\":\"It all depends\",\"publishedAt\":\"2019-12-17T15:59:00\",\"image\":null,\"customerNews\":[]}," +
        "\"employee\":{\"id\":null,\"firstName\":\"Hugo\",\"lastName\":\"Deval\",\"email\":\"hugo1@gmail.com\",\"password\":\"$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq\",\"locked\":false,\"isEmployee\":true,\"loginCount\":0}}";

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
