package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationTestData {
    Long ID = 6L;
    String TEST_FIRST_NAME = "Name";
    String TEST_LAST_NAME = "Nachname";
    String TEST_EMAIL = "name.nachname@email.com";
    String TEST_PASSWORD= "langesPassword";
    Boolean TEST_LOCKED= false;
    Boolean TEST_IS_EMPLOYEE = false;

    String BASE_URI = "/api/v1";
    String USER_BASE_URI = BASE_URI + "/user";

    String ADMIN_USER = "admin@email.com";
    String DEFAULT_USER = "user@email.com";


}
