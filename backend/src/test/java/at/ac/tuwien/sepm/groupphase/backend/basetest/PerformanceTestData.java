package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.util.ArrayList;
import java.util.List;

public interface PerformanceTestData {

    String BASE_URI = "/api/v1";
    String PERFORMANCE_BASE_URI = BASE_URI + "/performances";

    String  POST_REQUEST = "{\"event\":{\"id\":2,\"title\":\"Star Trek\",\"shortDescription\":\"May you live long ...\",\"contents\":\"May you live long and prosper\",\"category\":\"FILM\",\"duration\":2.4,\"employee\":{\"id\":2,\"firstName\":\"Susan\",\"lastName\":\"Bigfoot\",\"email\":\"susan@gmx.at\",\"password\":\"$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq\",\"locked\":false,\"isEmployee\":false,\"loginCount\":0},\"artists\":null}," +
        "\"room\":{\"id\":2,\"name\":\"Zweig\",\"location\":{\"id\":2,\"name\":\"Metropol\",\"street\":\"Karl-Krause-Strasse 45\",\"city\":\"Graz\",\"postalCode\":8020}}," +
        "\"date\":\"2019-12-11T21:47:53.163567\"}";

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
